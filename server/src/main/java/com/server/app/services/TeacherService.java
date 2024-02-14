package com.server.app.services;

import com.server.app.dtos.TeacherDetailDto;
import com.server.app.models.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    List<Teacher> findAll();
    void deleteTeacherById(Long id);
    Teacher save (Map<String,String> map);
    List<TeacherDetailDto> getTeachersByDetail();
}
