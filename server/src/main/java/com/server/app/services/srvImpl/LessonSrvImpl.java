package com.server.app.services.srvImpl;

import com.server.app.models.Lesson;
import com.server.app.repositories.GradeRepository;
import com.server.app.repositories.LessonRepository;
import com.server.app.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LessonSrvImpl implements LessonService {
    private final LessonRepository repository;
    private final GradeRepository gradeRepository;

    @Override
    public Lesson save(Map<String, String> map) {
        if(
                map.get("name") == null || map.get("countPerWeek") == null ||
                        repository.existsLessonByName(map.get("name"))
        ){
            throw new CommandAcceptanceException("Var olan Bir Nesne Tekrar Tanımlanamaz");
        }

        return repository.save(Lesson.builder().name(map.get("name"))
                .countPerWeek(Integer.parseInt(map.get("countPerWeek"))).build());
    }

    @Override
    public List<Lesson> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteLessonById(Long id) {
        gradeRepository.deleteGradeByLessonId(id);
        repository.deleteLessonById(id);
    }


}
