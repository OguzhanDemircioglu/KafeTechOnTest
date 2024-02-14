package com.server.app.services;

import com.server.app.dtos.LessonDetailDto;
import com.server.app.models.Lesson;

import java.util.List;
import java.util.Map;

public interface LessonService {
    List<Lesson> findAll();
    void deleteLessonById(Long id);
    Lesson save (Map<String,String> map);
    List<LessonDetailDto> getLessonsByDetail();
}
