package com.oauth_playground.service;

import com.oauth_playground.api.v1.model.PlaygroundUser;
import com.oauth_playground.api.v1.model.enums.Role;
import com.oauth_playground.api.v1.record.PlaygroundUserDTO;
import com.oauth_playground.api.v1.repository.PlaygroudUserRepository;
import com.oauth_playground.api.v1.service.PlaygroundUserServiceImpl;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class PlaygroundUserServiceImplTest {

    @Mock
    private PlaygroudUserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PlaygroundUserServiceImpl service;

    private PlaygroundUser existingUser;
    private PlaygroundUserDTO adminUser;
    private PlaygroundUserDTO normalUser;

    @BeforeEach
    public void setUp() {
        existingUser = PlaygroundUser.builder()
                .email("user@mail.com")
                .username("USER")
                .password("$%!").build();

        adminUser = PlaygroundUserDTO.builder()
                .isAdmin(true)
                .email("user@mail.com")
                .username("USER")
                .password("$%!").build();

        normalUser = PlaygroundUserDTO.builder()
                .isAdmin(false)
                .email("admin@mail.com")
                .username("ADMIN")
                .password("$*?").build();
    }

    @Test
    @DisplayName("JUnit test for createUser method")
    void givenPlaygroundObject_withUserRole_whenSave_thenReturnPlaygroundUserDTO() {
        given(repository.findByUsername(normalUser.username())).willReturn(Optional.empty());
        given(repository.findByEmail(normalUser.email())).willReturn(Optional.empty());
        given(repository.save(any(PlaygroundUser.class))).willReturn(null);

        Assertions.assertDoesNotThrow(() -> service.createUser(normalUser));

        then(repository).should().save(any(PlaygroundUser.class));
    }

    @Test
    @DisplayName("JUnit test for createUser method")
    void givenPlaygroundObject_withUserAdmin_whenSave_thenReturnPlaygroundUserDTO() {
        given(repository.findByUsername(adminUser.username())).willReturn(Optional.empty());
        given(repository.findByEmail(adminUser.email())).willReturn(Optional.empty());
        given(repository.save(any(PlaygroundUser.class))).willReturn(null);

        Assertions.assertDoesNotThrow(() -> service.createUser(adminUser));

        then(repository).should().save(any(PlaygroundUser.class));
    }

    @Test
    @DisplayName("Should throw exception when email exists")
    void givenExistingUsername_whenCreateUser_thenThrowException() {
        given(repository.findByUsername(normalUser.username())).willReturn(Optional.of(existingUser));

        assertThatThrownBy(() -> service.createUser(normalUser))
                .isInstanceOf(EntityExistsException.class)
                .hasMessageContaining("Username already exists");

        then(repository).should(never()).save(any());
    }

    @Test
    @DisplayName("Should throw exception when email exists")
    void givenExistingEmail_whenCreateUser_thenThrowException() {
        given(repository.findByUsername(normalUser.username())).willReturn(Optional.empty());
        given(repository.findByEmail(normalUser.email())).willReturn(Optional.of(existingUser));

        assertThatThrownBy(() -> service.createUser(normalUser))
                .isInstanceOf(EntityExistsException.class)
                .hasMessageContaining("Email already exists");

        then(repository).should(never()).save(any());
    }
}