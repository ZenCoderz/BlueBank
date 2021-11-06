package com.zencoderz.bluebank.api.user;

import com.zencoderz.bluebank.api.user.attributes.IdentifierType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
    Optional<User> findByIdentifierAndIdentifierType(String identifier, IdentifierType identifierType);

}
