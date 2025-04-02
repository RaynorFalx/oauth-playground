package com.oauth_playground.service;

import com.oauth_playground.mapper.PlaygroundUserMapper;
import com.oauth_playground.model.PlaygroundUser;
import com.oauth_playground.model.PlaygroundUser.PlaygroundUserBuilder;
import com.oauth_playground.model.enums.Role;
import com.oauth_playground.record.PlaygroundUserDTO;
import com.oauth_playground.repository.PlaygroudUserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaygroundUserServiceImpl implements PlaygroundService {

    private final PlaygroundUserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final PlaygroudUserRepository repository;

    @Override
    public PlaygroundUserDTO findUserById(Long id) {
        return mapper.toPlaygroundUserDTO(
                repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usernot find with id:" +  id))
        );
    }

    @Override
    public PlaygroundUserDTO findUserByEmail(String email) {
        return mapper.toPlaygroundUserDTO(
                repository.findByUsername(email).orElseThrow(() -> new EntityNotFoundException("Usernot find with email:" +  email))
        );
    }

    @Override
    public PlaygroundUserDTO findUserByUsername(String username) {
        return mapper.toPlaygroundUserDTO(
                repository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Usernot find with username:" + username))
        );
    }

    @Override
    public void createUser(PlaygroundUserDTO userDTO) {
        repository.save(applyRoleAndVerifyDuplicity(userDTO));
    }

    private PlaygroundUser applyRoleAndVerifyDuplicity(PlaygroundUserDTO userDTO) {
        if(repository.findByUsername(userDTO.username()).isPresent()) {
            throw new EntityExistsException("Username already exists:" +  userDTO.username());
        }

        if (repository.findByEmail(userDTO.email()).isPresent()) {
            throw new EntityExistsException("Email already exists:" +  userDTO.email());
        }

        return makePlaygroundUser(userDTO);
    }

    private PlaygroundUser makePlaygroundUser(PlaygroundUserDTO userDTO) {
        PlaygroundUserBuilder playgroundUser = PlaygroundUser.builder();
        playgroundUser.email(userDTO.email()).username(userDTO.username()).password(passwordEncoder.encode(userDTO.password()));

        if (userDTO.isAdmin()) {
            return playgroundUser.roles(List.of(Role.USER, Role.ADMIN)).build();
        }

        return playgroundUser.roles(List.of(Role.USER)).build();
    }
}
