package org.example.validator.custom_validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.custom_annotation.ExistStudentId;
import org.example.dao.StudentDAO;
import org.example.service.mapper_service.EntityMapperService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExistStudentIdValidator implements ConstraintValidator<ExistStudentId, Integer> {

    private final StudentDAO studentDAO;

    @Override
    public boolean isValid(Integer studentId, ConstraintValidatorContext constraintValidatorContext) {
        if (studentId != null && !studentDAO.existsById(studentId)) {
            log.info("Student with this ID: " + studentId + " not found");
            return false;
        }
        return true;
    }
}
