package com.codedecode.userinfo.dto;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int userId;
    @NotBlank(message = "Username cannot be blank")
    private String userName;
    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^[^*?]*$", message = "Password cannot contain * or ?")
    private String userPassword;
    private String address;
    private String city;
}
