package com.server.app.services;

import com.server.app.models.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Student> findAll();
    void deleteStudentById(Long id);
    Student save (Map<String,String> map);
}
