package com.oauth_playground.controller;

import com.oauth_playground.record.PlaygroundUserDTO;
import com.oauth_playground.service.PlaygroundUserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/user-management")
public class PlaygroundUserManagementController {

    private final PlaygroundUserServiceImpl service;

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody PlaygroundUserDTO user) {
        service.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
