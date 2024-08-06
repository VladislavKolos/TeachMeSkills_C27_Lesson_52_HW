package org.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.annotation.custom_annotation.ValidGrooup;
import org.example.model.Student;

import java.util.List;

@Getter
@Setter
@ValidGrooup
@NoArgsConstructor
@AllArgsConstructor
public class GrooupDTO {

    private int id;

    @Size(max = 20, min = 2)
    private String title;

    @Min(value = 1)
    private int room;

    private List<Student> students;
}
