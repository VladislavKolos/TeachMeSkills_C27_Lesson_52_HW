package org.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.annotation.custom_annotation.ValidStudent;
import org.example.model.Payment;

import java.util.List;

@Getter
@Setter
@ValidStudent
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private int id;

    @Size(max = 50, min = 2)
    private String name;

    @Size(min = 1)
    private String surname;

    @Min(value = 16)
    @Max(value = 62)
    private int age;

    private RecordBookDTO recordBookDTO;

    private GrooupDTO grooupDTO;

    private List<Payment> payments;
}
