package org.example.service.mapper_service;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.*;
import org.example.model.Group;
import org.example.model.Payment;
import org.example.model.RecordBook;
import org.example.model.Student;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityMapperService {
    public StudentResponseDTO convertToStudentResponseDto(Student student) {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setSurname(student.getSurname());
        studentResponseDTO.setAge(student.getAge());
        studentResponseDTO.setRecordBookDTO(convertToRecordBookDto(student.getRecordBook()));
        studentResponseDTO.setGroupDTO(convertToGroupDto(student.getGroup()));
        studentResponseDTO.setPaymentsDTO(student.getPayments().stream()
                .map(this::convertToPaymentDto).toList());

        return studentResponseDTO;
    }

    public PaymentDTO convertToPaymentDto(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        paymentDTO.setStatus(payment.getStatus());

        return paymentDTO;
    }

    public GroupDTO convertToGroupDto(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setName(group.getName());
        groupDTO.setRoom(group.getRoom());

        return groupDTO;
    }

    public RecordBookDTO convertToRecordBookDto(RecordBook recordBook) {
        RecordBookDTO recordBookDTO = new RecordBookDTO();
        recordBookDTO.setId(recordBook.getId());
        recordBookDTO.setRating(recordBook.getRating());

        return recordBookDTO;
    }

    public Student convertToStudentEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setAge(studentDTO.getAge());
        student.setPayments(studentDTO.getPaymentsDTO().stream()
                .map(this::convertToPaymentEntity).toList());
        student.setGroup(convertToGroupEntity(studentDTO.getGroupId()));
        student.setRecordBook(convertToRecordBookEntity(studentDTO.getRecordBookDTO()));

        return student;
    }

    public Payment convertToPaymentEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setStatus(paymentDTO.getStatus());

        return payment;
    }

    public Group convertToGroupEntity(Integer groupId) {
        Group group = new Group();
        group.setId(groupId);

        return group;
    }

    public RecordBook convertToRecordBookEntity(RecordBookDTO recordBookDTO) {
        RecordBook recordBook = new RecordBook();
        recordBook.setId(recordBookDTO.getId());
        recordBook.setRating(recordBookDTO.getRating());

        return recordBook;
    }
}
