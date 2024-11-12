package az.company.authms.service;

import az.company.authms.client.UserFeignClient;
import az.company.authms.dto.*;
import az.company.authms.dto.token.TokenResponse;
import az.company.authms.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserFeignClient userFeignClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;


    public TokenResponse loginUser(UserLoginRequest loginRequest) {

        Optional<UserDto> user = userFeignClient.getUserByFin(loginRequest.getFin());

        if (user.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return authenticateLogin(loginRequest);
    }

    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {


        // Encode password before sending it to the Feign Client
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        // Call Feign Client to register the user and return the response
        return userFeignClient.saveUser(userRegisterRequest);

    }

    private TokenResponse authenticateLogin(UserLoginRequest userLoginRequest) {
        TokenResponse tokenResponse = new TokenResponse();
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginRequest.getFin(), userLoginRequest.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);
        tokenResponse.setToken(jwtService.generateToken(userLoginRequest.getFin()));
        return tokenResponse;
    }
}