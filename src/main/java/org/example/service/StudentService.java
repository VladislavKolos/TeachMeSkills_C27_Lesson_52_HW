package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.StudentDAO;
import org.example.dto.StudentDTO;
import org.example.dto.StudentResponseDTO;
import org.example.model.Student;
import org.example.service.mapper_service.EntityMapperService;
import org.example.validator.custom_validator.ExistGroupIdValidator;
import org.example.validator.custom_validator.ExistStudentIdValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDAO studentDAO;

    private final EntityMapperService mapperService;

    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsByName(String name) {
        return studentDAO.findAllByName(name).stream().map(mapperService::convertToStudentResponseDto).toList();
    }

    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsBySurname(String surname) {
        return studentDAO.findAllBySurname(surname).stream().map(mapperService::convertToStudentResponseDto).toList();
    }

    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsByGroupId(Integer id) {
        return studentDAO.findAllByGroupId(id).stream().map(mapperService::convertToStudentResponseDto).toList();
    }

    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsByGroupName(String groupName) {
        return studentDAO.findAllByGroupName(groupName)
                .stream()
                .map(mapperService::convertToStudentResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsWithNoPayments() {
        return studentDAO.findByPaymentsIsEmpty().stream().map(mapperService::convertToStudentResponseDto).toList();
    }

    @Transactional
    public StudentResponseDTO createStudent(StudentDTO studentDTO) {
        return Optional.of(studentDTO)
                .map(mapperService::convertToStudentEntity)
                .map(studentDAO::save)
                .map(mapperService::convertToStudentResponseDto)
                .orElseThrow();
    }

    public StudentResponseDTO updateStudent(Integer id, Integer newAge) {

        return studentDAO.findById(id)
                .map(student -> student.setAge(newAge))
                .map(studentDAO::save)
                .map(mapperService::convertToStudentResponseDto)
                .orElseThrow(() -> new RuntimeException("User: " + id + " not found"));
    }

    @Transactional
    public void deleteStudent(Integer id) {
        studentDAO.deleteById(id);
    }

    @Transactional
    public StudentResponseDTO transferStudentToAnotherGroup(Integer id, Integer newGroupId) {
        studentDAO.updateGroupById(id, newGroupId);

        return studentDAO.findById(id).map(mapperService::convertToStudentResponseDto).orElseThrow(() -> {
            log.info("Student with student ID: " + id + " not found");
            return new RuntimeException("Student not found");
        });
    }
}
