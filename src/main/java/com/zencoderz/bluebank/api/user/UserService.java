package com.zencoderz.bluebank.api.user;

import com.zencoderz.bluebank.api.user.dto.UserDTO;
import com.zencoderz.bluebank.api.user.dto.UserFormCreateDTO;

import java.util.UUID;

public interface UserService {

    User findUserById(UUID userId);
    UserDTO saveUser(UserFormCreateDTO userFormCreateDTO);
    User getUser(String username);
    UserDTO getUserDTO(String username);
    void changeUserAuthority(String username, String authority) throws Exception;

}
