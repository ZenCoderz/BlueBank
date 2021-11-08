package com.zencoderz.bluebank.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserAuthorityDTO {

    @Schema(example = "admin@email.com")
    private String username;
    @Schema(example = "admin")
    private String authority;

}
