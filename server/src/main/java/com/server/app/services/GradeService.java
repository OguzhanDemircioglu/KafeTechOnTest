package com.server.app.services;

import com.server.app.dtos.GradeDto;
import com.server.app.models.Grade;

import java.util.List;
import java.util.Map;

public interface GradeService {

    Grade save(Map<String,String> map);

    List<Grade> findAll();

    void deleteGradeById(Long id);

    List<GradeDto> getGrades(Map<String,String> map);

    List<GradeDto> getAllGrades();
}
