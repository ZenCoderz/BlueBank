package com.zencoderz.bluebank.api.user;

import com.zencoderz.bluebank.api.user.dto.UserFormCreateDTO;

public interface UserService {

    User saveUser(UserFormCreateDTO userFormCreateDTO);
    User getUser(String username);
    void changeUserAuthority(String username, String authority) throws Exception;

}
