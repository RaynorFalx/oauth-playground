package com.oauth_playground.api.v1.service;

import com.oauth_playground.api.v1.record.PlaygroundUserDTO;

public interface PlaygroundService {
    PlaygroundUserDTO findUserById(Long id);
    PlaygroundUserDTO findUserByEmail(String email);
    PlaygroundUserDTO findUserByUsername(String username);
    void createUser(PlaygroundUserDTO user);
}
