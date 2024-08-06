package org.example.annotation.custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validator.custom_validator.CustomGrooupDTOValidator;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomGrooupDTOValidator.class)
@Documented
public @interface ValidGrooup {
    String message() default "Invalid Group data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
