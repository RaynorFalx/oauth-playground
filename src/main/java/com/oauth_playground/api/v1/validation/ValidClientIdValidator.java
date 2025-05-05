package com.oauth_playground.api.v1.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public class ValidClientIdValidator implements ConstraintValidator<ValidClientId, String> {

    @Autowired
    RegisteredClientRepository repository;

    @Override
    public boolean isValid(String clientId, ConstraintValidatorContext constraintValidatorContext) {
        return repository.findByClientId(clientId) != null;
    }
}
