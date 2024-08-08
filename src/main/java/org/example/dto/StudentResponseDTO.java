package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {

    private int id;

    private String name;

    private String surname;

    private int age;

    private GroupDTO groupDTO;

    private RecordBookDTO recordBookDTO;

    private List<PaymentDTO> paymentsDTO;
}
