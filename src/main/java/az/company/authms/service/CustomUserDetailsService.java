package az.company.authms.service;



import az.company.authms.client.UserFeignClient;
import az.company.authms.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User;

import java.util.Collections;
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String finCode) throws UsernameNotFoundException {
        UserDto userDto = userFeignClient.getUserByFin(finCode)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with finCode: " + finCode));

        return new User(

                userDto.getFullName(),
                userDto.getPassword(),
                Collections.emptyList()
        );
    }
}