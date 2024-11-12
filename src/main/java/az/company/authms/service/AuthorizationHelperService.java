package az.company.authms.service;

import az.company.authms.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationHelperService {
    private final JwtService jwtService;

    public String getFin(String authorization) {
        String token;
        String fin;
        token = authorization.substring(7);
        fin = jwtService.extractUsername(token);
        return fin;
    }
}