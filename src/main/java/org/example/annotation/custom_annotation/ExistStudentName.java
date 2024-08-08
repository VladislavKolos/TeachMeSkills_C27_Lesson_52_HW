package org.example.annotation.custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validator.custom_validator.ExistStudentNameValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistStudentNameValidator.class)
@Documented
public @interface ExistStudentName {
    String message() default "Invalid Student Name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
