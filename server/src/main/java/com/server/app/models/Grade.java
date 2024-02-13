package com.server.app.models;

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
@Table(name = "grades")
public class Grade {

    @Id
    @SequenceGenerator(name = "seq_note", allocationSize = 1)
    @GeneratedValue(generator = "seq_note", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "grade_score")
    private double gradeScore;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}
