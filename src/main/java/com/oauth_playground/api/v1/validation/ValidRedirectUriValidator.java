package com.oauth_playground.api.v1.validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

public class ValidRedirectUriValidator implements ConstraintValidator<ValidRedirectUri, String> {

    @Autowired
    private RegisteredClientRepository repository;

    @Override
    public boolean isValid(String uri, ConstraintValidatorContext constraintValidatorContext) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String clientId = request.getParameter("clientId");

        if (clientId == null) {
            return false;
        }

        RegisteredClient client = repository.findByClientId(clientId);
        return client != null && client.getRedirectUris().contains(uri);
    }
}
