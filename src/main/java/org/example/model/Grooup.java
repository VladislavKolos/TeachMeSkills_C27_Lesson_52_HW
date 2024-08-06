package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "grooup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grooup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 20, min = 2)
    @Column(name = "title")
    private String title;

    @Min(value = 1)
    @Column(name = "room")
    private int room;

    @OneToMany(mappedBy = "grooup")
    private List<Student> students;
}
