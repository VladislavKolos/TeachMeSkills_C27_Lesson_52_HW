package org.example.annotation.custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validator.custom_validator.ExistGroupIdValidator;
import org.example.validator.custom_validator.ExistGroupNameValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistGroupNameValidator.class)
@Documented
public @interface ExistGroupName {
    String message() default "Invalid Group Name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
