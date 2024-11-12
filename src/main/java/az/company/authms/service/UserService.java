package az.company.authms.service;

import az.company.authms.client.UserFeignClient;
import az.company.authms.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserFeignClient userFeignClient;

    public Optional<UserDto> findUserByFin(String fin){
        return userFeignClient.getUserByFin(fin);
    }
}