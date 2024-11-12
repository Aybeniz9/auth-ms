package az.company.authms.dto;

import jakarta.validation.constraints.Email;
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
public class UserRegisterRequest {

    @NotEmpty(message = "Full name cannot be empty")
    private String fullName;

    @NotEmpty(message = "FinCode cannot be empty")
    private String fin;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;
}


//    @NotEmpty(message = "Phone number cannot be empty")
//    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")
//    private String phoneNumber;
