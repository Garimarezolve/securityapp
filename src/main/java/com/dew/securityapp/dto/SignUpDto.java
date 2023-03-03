package com.dew.securityapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class SignUpDto {
    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotEmpty(message = "username can not be empty")
    private String username;

    @NotNull(message = "email can not be null")
    @Email(message = "please enter valid")
    private String email;

    @NotNull(message = "password can not be null")
    @NotEmpty(message = "password can not be empty")
    private String password;
}
