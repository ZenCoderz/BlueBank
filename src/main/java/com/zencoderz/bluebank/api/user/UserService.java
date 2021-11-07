package com.zencoderz.bluebank.api.user;

import com.zencoderz.bluebank.api.user.dto.UserDTO;
import com.zencoderz.bluebank.api.user.dto.UserFormCreateDTO;

import java.util.Set;


public interface UserService {

    User findUserById(Long userId);
    UserDTO saveUser(UserFormCreateDTO userFormCreateDTO);
    User getUser(String username);
    UserDTO getUserDTO(String username);
    Set<UserDTO> getUsersDTO();
    void changeUserAuthority(String username, String authority) throws Exception;

}
