package org.example.validator.custom_validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.custom_annotation.ValidGrooup;
import org.example.dao.GrooupDAO;
import org.example.dto.GrooupDTO;
import org.example.model.Grooup;
import org.example.service.mapper_service.EntityMapperService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomGrooupDTOValidator implements ConstraintValidator<ValidGrooup, GrooupDTO> {

    private final GrooupDAO grooupDAO;

    private final EntityMapperService mapperService;

    @Override
    public boolean isValid(GrooupDTO grooupDTO, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValidVar = true;

        constraintValidatorContext.disableDefaultConstraintViolation();

        Grooup grooup = mapperService.convertToGroupEntity(grooupDTO);

        if (!grooupDAO.existsById(grooup.getId())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Group with this ID does not exist")
                    .addPropertyNode("id")
                    .addConstraintViolation();
            log.error("Group with this ID: " + grooup.getId() + " does not exist");
            isValidVar = false;
        }

        if (!grooupDAO.existsByTitle(grooup.getTitle())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Group with this name does not exist")
                    .addPropertyNode("title")
                    .addConstraintViolation();
            log.error("Group with this name: " + grooup.getTitle() + "does not exist");
            isValidVar = false;
        }

        return isValidVar;
    }
}
