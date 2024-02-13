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
@Table(name = "lessons")
public class Lesson {

    @Id
    @SequenceGenerator(name = "seq_lesson", allocationSize = 1)
    @GeneratedValue(generator = "seq_lesson", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 20, name = "name")
    private String name;

    @Column(name = "countPerWeek")
    private int countPerWeek;

    @JsonIgnore
    @OneToMany(mappedBy = "lesson")
    private List<Grade> grades;
}
