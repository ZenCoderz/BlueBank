package com.zencoderz.bluebank.api.user;

import com.zencoderz.bluebank.api.user.attributes.Authority;
import com.zencoderz.bluebank.api.user.dto.UserFormCreateDTO;
import com.zencoderz.bluebank.auth.util.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {

    private AuthUtil authUtil;

    public User convertCreateFormToTransaction(UserFormCreateDTO userFormCreateDTO) {
        User user = new User();
        user.setName(userFormCreateDTO.getName());
        user.setUsername(userFormCreateDTO.getUsername());
        user.setPassword(authUtil.passwordEncoder().encode(userFormCreateDTO.getPassword()));
        user.setAuthority(Authority.APPUSER);
        user.setIdentifier(userFormCreateDTO.getIdentifier());
        user.setIdentifierType(userFormCreateDTO.getIdentifierType());
        return user;
    }

}
