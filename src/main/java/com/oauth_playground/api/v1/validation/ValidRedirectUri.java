package com.oauth_playground.api.v1.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidRedirectUriValidator.class)
public @interface ValidRedirectUri {
    String message() default "Unauthorized redirect URI";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
