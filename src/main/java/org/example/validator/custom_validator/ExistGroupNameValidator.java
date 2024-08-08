package org.example.validator.custom_validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.custom_annotation.ExistGroupName;
import org.example.dao.GroupDAO;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExistGroupNameValidator implements ConstraintValidator<ExistGroupName, String> {

    private final GroupDAO groupDAO;

    @Override
    public boolean isValid(String groupName, ConstraintValidatorContext context) {
        if (groupName!= null && !groupDAO.existsByName(groupName)) {
            log.info("Group with this name: " + groupName + " not found");
            return false;
        }
        return true;
    }
}
