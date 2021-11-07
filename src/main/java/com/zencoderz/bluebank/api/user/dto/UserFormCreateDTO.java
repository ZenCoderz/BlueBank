package com.zencoderz.bluebank.api.user.dto;

import com.zencoderz.bluebank.api.user.attributes.IdentifierType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFormCreateDTO {

    @Size(min=4, max=60, message = "Name size should be between 4 and 60")
    @NotBlank(message = "Name is required")
    @Schema(example = "user")
    private String name;

    @Email(message = "Username needs to be a valid email")
    @NotBlank(message = "Username is required")
    @Schema(example = "user@email.com")
    private String username;

    @Size(min=6, max=20, message = "Password size should be between 6 and 20")
    @NotBlank(message = "Password is required")
    @Schema(example = "password")
    private String password;

    @NotEmpty(message = "Identifier should not be null")
    @Schema(example = "11111111111")
    private String identifier;

    @NotNull(message = "Identifier Type should not be null")
    @Schema(example = "CPF")
    private IdentifierType identifierType;

}
