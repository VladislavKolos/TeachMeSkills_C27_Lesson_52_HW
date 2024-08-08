package org.example.validator.custom_validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.custom_annotation.ExistStudentSurname;
import org.example.dao.StudentDAO;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExistStudentSurnameValidator implements ConstraintValidator<ExistStudentSurname, String> {

    private final StudentDAO studentDAO;

    @Override
    public boolean isValid(String studentSurname, ConstraintValidatorContext context) {
        if (studentSurname != null && !studentDAO.existsBySurname(studentSurname)) {
            log.info("Student with this surname: " + studentSurname + " not found");
            return false;
        }
        return true;
    }
}
