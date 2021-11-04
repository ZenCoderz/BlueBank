package com.zencoderz.bluebank.auth.user;

import com.zencoderz.bluebank.auth.user.dto.UserDTO;
import com.zencoderz.bluebank.auth.user.dto.UserFormCreateDTO;

public interface UserService {

    User saveUser(UserFormCreateDTO userFormCreateDTO);
    User getUser(String username);
    void changeUserAuthority(String username, String authority) throws Exception;

}
