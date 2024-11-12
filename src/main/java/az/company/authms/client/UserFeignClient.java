package az.company.authms.client;

import az.company.authms.dto.UserDto;
import az.company.authms.dto.UserRegisterRequest;
import az.company.authms.dto.UserRegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
@FeignClient(name="ms-user",url="http://192.168.31.249:8083")
public interface UserFeignClient {

    @GetMapping("/{fin}")
    Optional<UserDto> getUserByFin(@PathVariable("fin") String fin);
    @PostMapping("/save")
    UserRegisterResponse saveUser(@RequestBody UserRegisterRequest userRegisterRequest);


}
