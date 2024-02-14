package com.server.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDetailDto {
    private Long id;
    private double gradeScore;
    private String lessonName;
    private String studentName;
    private String studentSurname;
    private String teacherName;
    private String teacherSurname;
    private double personalSuccessPercentage;
    private double classSuccessPercentage;
    private String note;
}
