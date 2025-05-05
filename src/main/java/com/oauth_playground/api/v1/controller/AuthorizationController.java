package com.oauth_playground.api.v1.controller;

import com.oauth_playground.api.v1.validation.ValidClientId;
import com.oauth_playground.api.v1.validation.ValidRedirectUri;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public interface AuthorizationController {
    ResponseEntity<String> getAuthorizationKey(

    );
}
