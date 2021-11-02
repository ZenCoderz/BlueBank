package com.zencoderz.bluebank.auth.user;

import com.zencoderz.bluebank.auth.config.dto.UserDTO;

public interface UserService {

    User saveUser(UserDTO userDTO);
    User getUser(String username);
    void changeUserAuthority(String username, String authority) throws Exception;

}
