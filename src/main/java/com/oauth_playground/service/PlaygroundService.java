package com.oauth_playground.service;

import com.oauth_playground.record.PlaygroundUserDTO;

public interface PlaygroundService {
    PlaygroundUserDTO findUserById(Long id);
    PlaygroundUserDTO findUserByEmail(String email);
    PlaygroundUserDTO findUserByUsername(String username);
    void createUser(com.oauth_playground.record.PlaygroundUserDTO user);
}
