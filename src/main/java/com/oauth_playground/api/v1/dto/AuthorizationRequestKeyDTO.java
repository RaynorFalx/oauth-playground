package com.oauth_playground.api.v1.dto;

import com.oauth_playground.api.v1.validation.ValidClientId;
import com.oauth_playground.api.v1.validation.ValidRedirectUri;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationRequestKeyDTO {
        @NotBlank(message = "Response Type cannot be blank")
        @Pattern(regexp = "code", message = "responseType must be 'code'")
        String responseType;

        @ValidClientId
        @NotBlank(message = "Client ID cannot be blank")
        String clientId;

        @ValidRedirectUri
        @NotBlank(message = "Redirect URI cannot be blank")
        String redirectUri;
}
