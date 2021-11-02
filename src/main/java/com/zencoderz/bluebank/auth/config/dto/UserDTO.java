package com.zencoderz.bluebank.auth.config.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class UserDTO {

    @Size(min=4, max=60, message = "Name size should be between 4 and 60")
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Username needs to be a valid email")
    @NotBlank(message = "Username is required")
    private String username;

    @Size(min=6, max=20, message = "Password size should be between 6 and 20")
    @NotBlank(message = "Password is required")
    private String password;

}
