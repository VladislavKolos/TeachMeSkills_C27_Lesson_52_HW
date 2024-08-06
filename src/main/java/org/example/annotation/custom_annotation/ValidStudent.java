package org.example.annotation.custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validator.custom_validator.CustomGrooupDTOValidator;
import org.example.validator.custom_validator.CustomStudentDTOValidator;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomStudentDTOValidator.class)
@Documented
public @interface ValidStudent {
    String message() default "Invalid Student data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
