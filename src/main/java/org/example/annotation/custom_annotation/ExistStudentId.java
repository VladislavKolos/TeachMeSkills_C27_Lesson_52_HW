package org.example.annotation.custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validator.custom_validator.ExistStudentIdValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistStudentIdValidator.class)
@Documented
public @interface ExistStudentId {
    String message() default "Invalid Student ID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
