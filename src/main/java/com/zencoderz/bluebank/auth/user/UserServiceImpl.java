package com.zencoderz.bluebank.auth.user;

import com.zencoderz.bluebank.auth.config.util.AuthUtil;
import com.zencoderz.bluebank.auth.user.attributes.Authority;
import com.zencoderz.bluebank.auth.config.dto.UserDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final AuthUtil util;

    public UserServiceImpl(UserRepository userRepository, AuthUtil util) {
        this.userRepository = userRepository;
        this.util = util;
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(util.passwordEncoder().encode(userDTO.getPassword()));
        user.setAuthority(Authority.APPUSER);
        return userRepository.save(user);
    }

    @Override
    public void changeUserAuthority(String username, String authority) throws Exception {
        if (isAuthorityNotValid(authority)) {
            throw new Exception("Authority doesn't exist");
        }
        User user = getUser(username);
        user.setAuthority(Authority.valueOf(authority.toUpperCase()));
    }

    @Override
    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getAuthority().toString());
        Collection<SimpleGrantedAuthority> grantedAuthorities = List.of(authority);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    private boolean isAuthorityNotValid(String authority) {
        return !Authority.contains(authority);
    }

}
