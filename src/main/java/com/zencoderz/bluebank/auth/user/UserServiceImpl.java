package com.zencoderz.bluebank.auth.user;

import com.zencoderz.bluebank.auth.config.util.AuthUtil;
import com.zencoderz.bluebank.auth.user.attributes.Authority;
import com.zencoderz.bluebank.auth.user.attributes.IdentifierType;
import com.zencoderz.bluebank.auth.user.dto.UserFormCreateDTO;
import com.zencoderz.bluebank.exception.InvalidInputException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    public User saveUser(UserFormCreateDTO userFormCreateDTO) {
        if (this.userIdentifierAlreadyExists(userFormCreateDTO.getIdentifier(), userFormCreateDTO.getIdentifierType())) {
            throw new InvalidInputException("Identifier already exists");
        }
        User user = new User();
        user.setName(userFormCreateDTO.getName());
        user.setUsername(userFormCreateDTO.getUsername());
        user.setPassword(util.passwordEncoder().encode(userFormCreateDTO.getPassword()));
        user.setAuthority(Authority.APPUSER);
        user.setIdentifier(userFormCreateDTO.getIdentifier());
        user.setIdentifierType(userFormCreateDTO.getIdentifierType());
        return userRepository.save(user);
    }

    @Override
    public void changeUserAuthority(String username, String authority) {
        if (isAuthorityNotValid(authority)) {
            throw new InvalidInputException("Authority doesn't exist");
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

    private boolean userIdentifierAlreadyExists(String identifier, IdentifierType identifierType) {
        Optional<User> optionalUser = this.userRepository.findByIdentifierAndIdentifierType(identifier, identifierType);
        return optionalUser.isPresent();
    }

    private boolean isAuthorityNotValid(String authority) {
        return !Authority.contains(authority);
    }

}
