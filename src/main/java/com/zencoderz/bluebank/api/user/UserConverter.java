package com.zencoderz.bluebank.api.user;

import com.zencoderz.bluebank.api.user.attributes.Authority;
import com.zencoderz.bluebank.api.user.dto.UserDTO;
import com.zencoderz.bluebank.api.user.dto.UserFormCreateDTO;
import com.zencoderz.bluebank.auth.util.AuthUtil;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserConverter {

    private AuthUtil authUtil;

    public User convertCreateFormToTransaction(UserFormCreateDTO userFormCreateDTO) {
        User user = new User();
        if (userFormCreateDTO == null) {
            return user;
        }
        user.setName(userFormCreateDTO.getName());
        user.setUsername(userFormCreateDTO.getUsername());
        user.setPassword(authUtil.passwordEncoder().encode(userFormCreateDTO.getPassword()));
        user.setAuthority(Authority.APPUSER);
        user.setIdentifier(userFormCreateDTO.getIdentifier());
        user.setIdentifierType(userFormCreateDTO.getIdentifierType());
        return user;
    }

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        if (user == null) {
            return userDTO;
        }
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setIdentifier(user.getIdentifier());
        userDTO.setIdentifierType(user.getIdentifierType());
        return userDTO;
    }

    public Set<UserDTO> convertUsersToDTO (List<User> users) {
        if (users == null) {
            return new HashSet<>();
        }
        return users.stream().map(this::userToUserDTO).collect(Collectors.toSet());
    }

}
