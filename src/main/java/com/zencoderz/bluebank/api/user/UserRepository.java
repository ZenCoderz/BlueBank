package com.zencoderz.bluebank.api.user;

import com.zencoderz.bluebank.api.user.attributes.IdentifierType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    Optional<User> findByIdentifierAndIdentifierType(String identifier, IdentifierType identifierType);
    List<User> findAllByOrderById();

}
