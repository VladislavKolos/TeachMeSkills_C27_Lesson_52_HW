package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 50, min = 2)
    @Column(name = "name")
    private String name;

    @Size(min = 1)
    @Column(name = "surname")
    private String surname;

    @Min(value = 16)
    @Max(value = 62)
    @Column(name = "age")
    private int age;

    @OneToOne
    @JoinColumn(name = "record_book_id")
    private RecordBook recordBook;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Grooup grooup;

    @OneToMany(mappedBy = "student")
    private List<Payment> payments;

}
