package com.server.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto {
    private double gradeScore;
    private String lessonName;
    private String studentName;
    private String teacherName;
}
