package com.server.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDetailDto {
    private Long id;

    private String name;

    private int countPerWeek;

    private long teacherCount;

    private long studentCount;

    private double maxGrade;

    private double avarageGrade;
}
