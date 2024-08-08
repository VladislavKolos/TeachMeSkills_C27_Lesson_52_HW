package org.example.validator.custom_validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.custom_annotation.ExistGroupId;
import org.example.dao.GroupDAO;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExistGroupIdValidator implements ConstraintValidator<ExistGroupId, Integer> {

    private final GroupDAO groupDAO;

    @Override
    public boolean isValid(Integer groupId, ConstraintValidatorContext constraintValidatorContext) {
        if (groupId != null && !groupDAO.existsById(groupId)) {
            log.info("Group with this ID: " + groupId + " not found");
            return false;
        }
        return true;
    }
}
