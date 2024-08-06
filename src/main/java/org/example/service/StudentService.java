package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.StudentDAO;
import org.example.dto.GrooupDTO;
import org.example.dto.StudentDTO;
import org.example.model.Grooup;
import org.example.model.Student;
import org.example.service.mapper_service.EntityMapperService;
import org.example.validator.custom_validator.CustomStudentDTOValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDAO studentDAO;

    private final EntityMapperService mapperService;

    private final CustomStudentDTOValidator customValidator;

    @Transactional(readOnly = true)
    public StudentDTO getStudentByName(String name) {
        if (!customValidator.isExistStudentName(name)) {
            log.error("Student with this name: " + name + " does not exist");
            throw new RuntimeException("Student with this name: " + name + " does not exist");
        }

        Optional<Student> student = studentDAO.findByName(name);

        return student.map(mapperService::convertToStudentDto).orElseThrow(() -> {
            log.error("Student with name: " + name + " not found");
            return new RuntimeException("Student not found");
        });
    }

    @Transactional(readOnly = true)
    public StudentDTO getStudentBySurname(String surname) {
        if (!customValidator.isExistStudentSurname(surname)) {
            log.error("Student with this surname: " + surname + " does not exist");
            throw new RuntimeException("Student with this surname: " + surname + " does not exist");
        }

        Optional<Student> student = studentDAO.findBySurname(surname);

        return student.map(mapperService::convertToStudentDto).orElseThrow(() -> {
            log.error("Student with surname: " + surname + " not found");
            return new RuntimeException("Student not found");
        });
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByGroupId(GrooupDTO grooupDTO) {
        Grooup grooup = mapperService.convertToGroupEntity(grooupDTO);

        List<Student> students = studentDAO.findByGrooupId(grooup.getId());

        List<StudentDTO> studentsDTO = students.stream().map(mapperService::convertToStudentDto).toList();

        if (studentsDTO.isEmpty()) {
            log.error("Students from group with group ID: " + grooup.getId() + " not found");
            throw new RuntimeException("Students not found");
        }
        return studentsDTO;
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByGroupTitle(GrooupDTO grooupDTO) {
        Grooup grooup = mapperService.convertToGroupEntity(grooupDTO);

        List<Student> students = studentDAO.findByGrooupTitle(grooup.getTitle());

        List<StudentDTO> studentsDTO = students.stream().map(mapperService::convertToStudentDto).toList();

        if (studentsDTO.isEmpty()) {
            log.error("Students from group: " + grooup.getTitle() + " not found");
            throw new RuntimeException("Students not found");
        }
        return studentsDTO;
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsWithNoPayments() {
        List<Student> students = studentDAO.findByPaymentsIsEmpty();

        List<StudentDTO> studentsDTO = students.stream().map(mapperService::convertToStudentDto).toList();

        if (studentsDTO.isEmpty()) {
            log.error("Students with no payments not found");
            throw new RuntimeException("Students with no payments not found");
        }
        return studentsDTO;
    }

    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = mapperService.convertToStudentEntity(studentDTO);

        Student createdStudent = studentDAO.save(student);

        return mapperService.convertToStudentDto(createdStudent);
    }

    public StudentDTO updateStudent(int newAge, StudentDTO studentDTO) {
        Student student = mapperService.convertToStudentEntity(studentDTO);

        student.setAge(newAge);
        Student updatedStudent = studentDAO.save(student);

        return mapperService.convertToStudentDto(updatedStudent);
    }

    @Transactional
    public void deleteStudent(StudentDTO studentDTO) {
        Student student = mapperService.convertToStudentEntity(studentDTO);

        studentDAO.deleteById(student.getId());
    }

    @Transactional
    public StudentDTO transferStudentToAnotherGroup(StudentDTO studentDTO, int newGroupId) {
        Student student = mapperService.convertToStudentEntity(studentDTO);

        studentDAO.updateGrooupById(student.getId(), newGroupId);

        Optional<Student> finalStudent = studentDAO.findById(student.getId());

        return finalStudent.map(mapperService::convertToStudentDto).orElseThrow(() -> {
            log.error("Student with student ID: " + student.getId() + " not found");
            return new RuntimeException("Student not found");
        });
    }
}
