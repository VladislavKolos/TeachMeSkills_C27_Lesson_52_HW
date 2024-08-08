package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.custom_annotation.*;
import org.example.dto.StudentDTO;
import org.example.dto.StudentResponseDTO;
import org.example.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Student Management", description = "API for managing Students")
@Slf4j
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentRestController {
    private final StudentService studentService;

    @Operation(summary = "Get Students by Student name", description = "Enter the Student name to find Students")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByName(@NotNull @ExistStudentName @PathVariable String name) {
        List<StudentResponseDTO> studentDTO = studentService.getStudentsByName(name);

        log.info("Students by name: " + name + " successfully received");
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get Students by Student Surname", description = "Enter the Student Surname to find Students")
    @GetMapping("/surname/{surname}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsBySurname(@NotNull @ExistStudentSurname @PathVariable String surname) {
        List<StudentResponseDTO> studentDTO = studentService.getStudentsBySurname(surname);

        log.info("Students by surname: " + surname + " successfully received");
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get Students by group ID", description = "Enter the group ID to find Students int this group")
    @GetMapping("/groups/{id}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByGroupId(@NotNull @ExistGroupId @PathVariable Integer id) {
        List<StudentResponseDTO> studentsDTO = studentService.getStudentsByGroupId(id);

        log.info("Students by group ID: " + id + " successfully received");
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @Operation(summary = "get Students by group name", description = "Enter the group name to find Students in this group")
    @GetMapping("/groups/title/{groupName}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByGroupName(@NotNull @ExistGroupName @PathVariable String groupName) {
        List<StudentResponseDTO> studentsDTO = studentService.getStudentsByGroupName(groupName);

        log.info("Students by group name: " + groupName + " successfully received");
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get Students with no payments", description = "Find students who have not paid their tuition fees")
    @GetMapping("/no-payments")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsWithNoPayments() {
        List<StudentResponseDTO> studentsDTO = studentService.getStudentsWithNoPayments();

        log.info("Students with no payments successfully received");
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @Operation(summary = "Create Student", description = "Creates a new student based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student has been created successfully"),
            @ApiResponse(responseCode = "400", description = "Incorrect data")
    })
    @PostMapping("/create")
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        StudentResponseDTO createdStudentDTO = studentService.createStudent(studentDTO);

        log.info("Students successfully created");
        return new ResponseEntity<>(createdStudentDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Student age", description = "Updates a student's age based on his ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The student's age was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "400", description = "Incorrect data")
    })
    @PutMapping("/{id}/update")
    public ResponseEntity<StudentResponseDTO> updateStudent(@NotNull @ExistStudentId @PathVariable Integer id,
                                                            @NotNull @Min(16) @Max(62) @RequestParam Integer newAge) {

        StudentResponseDTO updatedStudentDTO = studentService.updateStudent(id, newAge);

        log.info("Students successfully updated");
        return new ResponseEntity<>(updatedStudentDTO, HttpStatus.OK);
    }

    @Operation(summary = "Delete Student", description = "Deletes a student by his ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@NotNull @ExistStudentId @PathVariable Integer id) {
        studentService.deleteStudent(id);

        log.info("Students successfully deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Transfer Student to another group", description = "Transfers a Student to another group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student successfully transferred"),
            @ApiResponse(responseCode = "404", description = "Student or group not found"),
            @ApiResponse(responseCode = "400", description = "Incorrect data")
    })
    @PutMapping("/{id}/transfer")
    public ResponseEntity<StudentResponseDTO> transferStudentToAnotherGroup(@NotNull @ExistStudentId @PathVariable Integer id,
                                                                            @NotNull @ExistGroupId @RequestParam Integer newGroupId) {

        StudentResponseDTO transferredStudentDTO = studentService.transferStudentToAnotherGroup(id, newGroupId);

        log.info("Student successfully transferred");
        return new ResponseEntity<>(transferredStudentDTO, HttpStatus.OK);
    }
}
