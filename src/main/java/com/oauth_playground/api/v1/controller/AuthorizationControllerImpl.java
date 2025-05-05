package com.oauth_playground.api.v1.controller;

import com.oauth_playground.api.v1.dto.AuthorizationRequestKeyDTO;
import com.oauth_playground.api.v1.validation.ValidClientId;
import com.oauth_playground.api.v1.validation.ValidRedirectUri;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/oauth2")
public class AuthorizationControllerImpl {

    @GetMapping("/access-key")
    public ResponseEntity<String> getAuthorizationKey(
            @ValidClientId
            @NotBlank(message = "Client ID cannot be blank")
            @RequestParam("clientId") String clientId,

            @ValidRedirectUri
            @NotBlank(message = "Redirect URI cannot be blank")
            @RequestParam("redirectUri") String redirectUri,

            @Pattern(regexp = "code", message = "responseType must be 'code'")
            @NotBlank(message = "Response Type cannot be blank")
            @RequestParam("responseType") String responseType,

            @RequestParam("codeChallenge") String codeChallenge
    ) {
        return new ResponseEntity<>(makeAccessKey(), HttpStatus.OK);
    }

    private String makeAccessKey() {
        return new OAuth2AuthorizationCode(
                UUID.randomUUID().toString(), Instant.now(), Instant.now().plusSeconds(300)
        ).getTokenValue();
    }
}
