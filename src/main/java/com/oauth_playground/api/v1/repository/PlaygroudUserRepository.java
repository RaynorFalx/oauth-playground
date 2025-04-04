package com.oauth_playground.api.v1.repository;

import com.oauth_playground.api.v1.model.PlaygroundUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaygroudUserRepository extends JpaRepository<PlaygroundUser, Long> {
    Optional<PlaygroundUser> findByUsername(String username);
    Optional<PlaygroundUser> findByEmail(String email);
}
