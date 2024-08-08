package org.example.validator.custom_validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.custom_annotation.ExistStudentName;
import org.example.dao.StudentDAO;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExistStudentNameValidator implements ConstraintValidator<ExistStudentName, String> {

    private final StudentDAO studentDAO;

    @Override
    public boolean isValid(String studentName, ConstraintValidatorContext context) {
        if (studentName != null && !studentDAO.existsByName(studentName)) {
            log.info("Student with this name: " + studentName + " not found");
            return false;
        }
        return true;
    }
}
