package com.server.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "students")
public class Student {

    @Id
    @SequenceGenerator(name = "seq_student", allocationSize = 1)
    @GeneratedValue(generator = "seq_student", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 20, name = "name")
    private String name;

    @Column(length = 20, name = "surname")
    private String surname;

    @Column(length = 11, name = "tckn")
    private String tckn;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Grade> grades;
}
