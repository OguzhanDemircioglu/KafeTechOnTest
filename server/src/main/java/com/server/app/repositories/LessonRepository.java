package com.server.app.repositories;

import com.server.app.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {

    Lesson getLessonById(Long id);

    boolean existsLessonByName(String name);

    void deleteLessonById(Long id);

}
