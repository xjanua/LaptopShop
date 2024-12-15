package com.example.Laptopshop.services.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = RegisterValidator.class)
@Target({ ElementType.TYPE }) // Annotation applied at the class level
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterChecked {

    String message() default "User registration validation failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}