package com.zencoderz.bluebank.auth.util;

import com.auth0.jwt.algorithms.Algorithm;
import com.zencoderz.bluebank.api.user.User;
import com.zencoderz.bluebank.api.user.attributes.Authority;
import com.zencoderz.bluebank.exception.AuthorizationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AuthUtil {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }

    public Algorithm algorithm() {
        return Algorithm.HMAC256("secret".getBytes());
    }

    public Date jwtTimeToLive() {
        return new Date(System.currentTimeMillis() + 10 * 60 * 1000);
    }

    public void isUserAllowed(List<Authority> notAllowedAuthorities, User user) {
        notAllowedAuthorities.forEach( authority -> {
            if (user.getAuthority().equals(authority)) {
               throw new AuthorizationException("User " + user.getName() + "is not allowed to do this action");
            }
        });
    }
}
