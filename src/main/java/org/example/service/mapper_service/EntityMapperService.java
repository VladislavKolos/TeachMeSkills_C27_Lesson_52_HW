package org.example.service.mapper_service;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.GrooupDTO;
import org.example.dto.PaymentDTO;
import org.example.dto.RecordBookDTO;
import org.example.dto.StudentDTO;
import org.example.model.Grooup;
import org.example.model.Payment;
import org.example.model.RecordBook;
import org.example.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class EntityMapperService {
    public StudentDTO convertToStudentDto(Student student) {
        if (student == null) {
            log.error("Student does not exist");
            throw new RuntimeException("Student does not exist");
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setAge(student.getAge());
        studentDTO.setRecordBookDTO(convertToRecordBookDto(student.getRecordBook()));
        studentDTO.setGrooupDTO(convertToGrooupDto(student.getGrooup()));

        return studentDTO;
    }

    public PaymentDTO convertToPaymentDto(Payment payment) {
        if (payment == null) {
            log.error("Payment does not exist");
            throw new RuntimeException("Payment does not exist");
        }
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        paymentDTO.setStatus(payment.getStatus());
        paymentDTO.setStudentDTO(convertToStudentDto(payment.getStudent()));

        return paymentDTO;
    }

    public GrooupDTO convertToGrooupDto(Grooup grooup) {
        if (grooup == null) {
            log.error("Group does not exist");
            throw new RuntimeException("Group does not exist");
        }
        GrooupDTO grooupDTO = new GrooupDTO();
        grooupDTO.setId(grooup.getId());
        grooupDTO.setTitle(grooup.getTitle());
        grooupDTO.setRoom(grooup.getRoom());

        return grooupDTO;
    }

    public RecordBookDTO convertToRecordBookDto(RecordBook recordBook) {
        if (recordBook == null) {
            log.error("Record book does not exist");
            throw new RuntimeException("Record book does not exist");
        }
        RecordBookDTO recordBookDTO = new RecordBookDTO();
        recordBookDTO.setId(recordBook.getId());
        recordBookDTO.setRating(recordBook.getRating());

        return recordBookDTO;
    }

    public Student convertToStudentEntity(StudentDTO studentDTO) {
        if (studentDTO == null) {
            log.error("Student does not exist");
            throw new RuntimeException("Student does not exist");
        }
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setAge(studentDTO.getAge());
        student.setPayments(studentDTO.getPayments());
        student.setGrooup(convertToGroupEntity(studentDTO.getGrooupDTO()));
        student.setRecordBook(convertToRecordBookEntity(studentDTO.getRecordBookDTO()));

        return student;
    }

    public List<Payment> convertToPaymentEntity(PaymentDTO paymentDTO) {
        if (paymentDTO == null) {
            log.error("Payment does not exist");
            throw new RuntimeException("Payment does not exist");
        }
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setStatus(paymentDTO.getStatus());
        payment.setStudent(convertToStudentEntity(paymentDTO.getStudentDTO()));

        List<Payment> payments = new ArrayList<>();
        payments.add(payment);

        return payments;
    }

    public Grooup convertToGroupEntity(GrooupDTO grooupDTO) {
        if (grooupDTO == null) {
            log.error("Group does not exist");
            throw new RuntimeException("Group does not exist");
        }
        Grooup grooup = new Grooup();
        grooup.setId(grooupDTO.getId());
        grooup.setTitle(grooupDTO.getTitle());
        grooup.setRoom(grooupDTO.getRoom());

        return grooup;
    }

    public RecordBook convertToRecordBookEntity(RecordBookDTO recordBookDTO) {
        if (recordBookDTO == null) {
            log.error("Record book does not exist");
            throw new RuntimeException("Record book does not exist");
        }
        RecordBook recordBook = new RecordBook();
        recordBook.setId(recordBookDTO.getId());
        recordBook.setRating(recordBookDTO.getRating());

        return recordBook;
    }
}
