package com.server.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailDto {

    private Long id;

    private String name;

    private String surname;

    private String tckn;

    long lessonCount;

    double maxGrade;

    double avarageGrade;
}
