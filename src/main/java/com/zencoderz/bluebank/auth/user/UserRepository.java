package com.zencoderz.bluebank.auth.user;

import com.zencoderz.bluebank.auth.user.attributes.IdentifierType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByIdentifierAndIdentifierType(String identifier, IdentifierType identifierType);
}
