package com.zencoderz.bluebank.factory;

import com.zencoderz.bluebank.api.user.User;
import com.zencoderz.bluebank.api.user.attributes.IdentifierType;
import com.zencoderz.bluebank.api.user.dto.UserFormCreateDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserTestUtil extends TestUtilWithUniqueIdentifier {

    public User buildUser() {
        User user = new User();
        user.setName("Name " + this.getUniqueIdentifier());
        user.setUsername("username" + this.getUniqueIdentifier() + "@test.com ");
        user.setPassword("123456");
        user.setIdentifier("111.111.111-1 " + this.getUniqueIdentifier());
        user.setIdentifierType(IdentifierType.CPF);
        return user;
    }

    public UserFormCreateDTO buildUserFormCreateDTO() {
        UserFormCreateDTO form = new UserFormCreateDTO();
        form.setName("User Test");
        form.setUsername("user@test.com");
        form.setPassword("123456");
        form.setIdentifier("000.000.000-11");
        form.setIdentifierType(IdentifierType.CPF);
        return form;
    }

}
