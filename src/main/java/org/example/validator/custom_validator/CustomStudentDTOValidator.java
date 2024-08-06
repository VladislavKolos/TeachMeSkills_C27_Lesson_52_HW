package org.example.validator.custom_validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.custom_annotation.ValidStudent;
import org.example.dao.StudentDAO;
import org.example.dto.StudentDTO;
import org.example.model.Student;
import org.example.service.mapper_service.EntityMapperService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomStudentDTOValidator implements ConstraintValidator<ValidStudent, StudentDTO> {

    private final StudentDAO studentDAO;

    private final EntityMapperService mapperService;

    @Override
    public boolean isValid(StudentDTO studentDTO, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValidVar = true;

        constraintValidatorContext.disableDefaultConstraintViolation();

        Student student = mapperService.convertToStudentEntity(studentDTO);

        if (studentDAO.existsById(student.getId())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Student with this ID already exists")
                    .addPropertyNode("id")
                    .addConstraintViolation();
            log.error("Student with this ID: " + student.getId() + " already exists");
            isValidVar = false;
        }

        if (studentDAO.existsByName(student.getName())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Student with this name already exists")
                    .addPropertyNode("name")
                    .addConstraintViolation();
            log.error("Student with this name: " + student.getName() + " already exists");
            isValidVar = false;
        }

        if (studentDAO.existsBySurname(student.getSurname())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Student with this surname already exists")
                    .addPropertyNode("surname")
                    .addConstraintViolation();
            log.error("Student with this surname: " + student.getSurname() + " already exists");
            isValidVar = false;
        }

        if (studentDAO.existsByAge(student.getAge())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Student with this age already exists")
                    .addPropertyNode("age")
                    .addConstraintViolation();
            log.error("Student with this age: " + student.getAge() + " already exists");
            isValidVar = false;
        }
        return isValidVar;
    }

    public boolean isExistStudentName(String name) {
        return studentDAO.existsByName(name);
    }

    public boolean isExistStudentSurname(String surname) {
        return studentDAO.existsBySurname(surname);
    }
}
