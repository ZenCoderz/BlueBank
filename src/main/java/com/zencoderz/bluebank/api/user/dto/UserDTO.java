package com.zencoderz.bluebank.api.user.dto;

import com.zencoderz.bluebank.api.user.attributes.IdentifierType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private String username;

    private String identifier;

    private IdentifierType identifierType;

}
