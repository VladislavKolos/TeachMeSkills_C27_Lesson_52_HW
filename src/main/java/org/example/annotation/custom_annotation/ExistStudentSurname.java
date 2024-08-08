package org.example.annotation.custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validator.custom_validator.ExistStudentSurnameValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistStudentSurnameValidator.class)
@Documented
public @interface ExistStudentSurname {
    String message() default "Invalid Student Surname";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
