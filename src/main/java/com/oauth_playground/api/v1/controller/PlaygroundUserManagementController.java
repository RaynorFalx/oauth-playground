package com.oauth_playground.api.v1.controller;

import com.oauth_playground.api.v1.record.PlaygroundUserDTO;
import com.oauth_playground.api.v1.service.PlaygroundUserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user-management")
public class PlaygroundUserManagementController {

    private final PlaygroundUserServiceImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<PlaygroundUserDTO> getUser(@PathVariable long id) {
        return new ResponseEntity<>(service.findUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody PlaygroundUserDTO user) {
        service.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
