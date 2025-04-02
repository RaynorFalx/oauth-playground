package com.oauth_playground.repository;

import com.oauth_playground.model.PlaygroundUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaygroudUserRepository extends JpaRepository<PlaygroundUser, Long> {
    Optional<PlaygroundUser> findByUsername(String username);
    Optional<PlaygroundUser> findByEmail(String email);
}
