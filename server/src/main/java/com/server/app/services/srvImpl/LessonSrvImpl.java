package com.server.app.services.srvImpl;

import com.server.app.dtos.LessonDetailDto;
import com.server.app.dtos.StudentDetailDto;
import com.server.app.models.Grade;
import com.server.app.models.Lesson;
import com.server.app.models.Student;
import com.server.app.repositories.GradeRepository;
import com.server.app.repositories.LessonRepository;
import com.server.app.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<LessonDetailDto> getLessonsByDetail() {

        List<LessonDetailDto> returnList = new ArrayList<>();

        for (Lesson t : repository.findAll()){
            LessonDetailDto dto = new LessonDetailDto();
            dto.setId(t.getId());
            dto.setName(t.getName());
            dto.setCountPerWeek(t.getCountPerWeek());

            List<Grade> list = gradeRepository.getGradesByLessonId(t.getId());
            dto.setTeacherCount(
                    list.stream().map(Grade::getTeacher).collect(Collectors.toSet()).size()
            );
            dto.setStudentCount(
                    list.stream().map(Grade::getStudent).collect(Collectors.toSet()).size()
            );
            dto.setMaxGrade(
                    list.stream().map(Grade::getGradeScore).max(Double::compareTo).orElse(0.0)
            );
            dto.setAvarageGrade(
                    list.stream()
                            .mapToDouble(Grade::getGradeScore)
                            .average()
                            .orElse(0.0)
            );
            returnList.add(dto);
        }
        return returnList;
    }
}
