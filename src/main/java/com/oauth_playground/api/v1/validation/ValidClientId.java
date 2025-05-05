package com.oauth_playground.api.v1.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidClientIdValidator.class)
public @interface ValidClientId {
    String message() default "Unregistered client";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
