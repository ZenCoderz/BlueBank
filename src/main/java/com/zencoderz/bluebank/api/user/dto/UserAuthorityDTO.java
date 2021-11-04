package com.zencoderz.bluebank.api.user.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserAuthorityDTO {

    private String username;
    private String authority;

}
