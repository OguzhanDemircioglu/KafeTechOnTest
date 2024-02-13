package com.server.app.services;

import com.server.app.models.Lesson;

import java.util.List;
import java.util.Map;

public interface LessonService {
    List<Lesson> findAll();
    void deleteLessonByName(String name);
    Lesson save (Map<String,String> map);

}
