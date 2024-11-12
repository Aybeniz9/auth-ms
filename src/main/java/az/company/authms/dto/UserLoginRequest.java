package az.company.authms.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequest {


        @NotEmpty(message = "fin cannot be empty")
        private String fin;

        @NotEmpty(message = "Password cannot be empty")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        private String password;
}
