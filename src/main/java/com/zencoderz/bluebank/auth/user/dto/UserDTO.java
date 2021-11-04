package com.zencoderz.bluebank.auth.user.dto;

import com.zencoderz.bluebank.auth.user.attributes.IdentifierType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;

    private String username;

    private String identifier;

    private IdentifierType identifierType;

}
