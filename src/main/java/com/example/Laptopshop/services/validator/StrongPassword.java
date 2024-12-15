package com.example.Laptopshop.services.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {

    String message() default "Password must be at least 8 characters long and contain a combination of uppercase, lowercase, numbers, and special characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}