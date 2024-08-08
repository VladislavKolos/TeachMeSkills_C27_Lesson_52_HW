package org.example.annotation.custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validator.custom_validator.ExistGroupIdValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistGroupIdValidator.class)
@Documented
public @interface ExistGroupId {
    String message() default "Invalid Group ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
