package com.server.app.services.srvImpl;

import com.server.app.dtos.GradeDto;
import com.server.app.models.Grade;
import com.server.app.repositories.GradeRepository;
import com.server.app.repositories.LessonRepository;
import com.server.app.repositories.StudentRepository;
import com.server.app.repositories.TeacherRepository;
import com.server.app.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GradeSrvImpl implements GradeService {
    private final GradeRepository repository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final LessonRepository lessonRepository;

    @Override
    public Grade save(Map<String, String> map) {

        if(
                map.get("gradeNumber") == null||
                map.get("teacherTckn") == null ||
                map.get("studentTckn") == null ||
                map.get("lessonName") == null ||
                        !studentRepository.existsStudentByTckn(map.get("studentTckn")) ||
                        !teacherRepository.existsTeacherByTckn(map.get("teacherTckn")) ||
                        !lessonRepository.existsLessonByName(map.get("lessonName"))
        ){
            throw new CommandAcceptanceException("Var olan Bir Nesne Tekrar Tanımlanamaz");
        }

        Grade grade = Grade.builder()
                .gradeScore(Double.parseDouble(map.get("gradeNumber")))
                .teacher(teacherRepository.getTeacherByTckn(map.get("teacherTckn")))
                .student(studentRepository.getStudentByTckn(map.get("studentTckn")))
                .lesson(lessonRepository.getLessonByName(map.get("lessonName")))
                .build();

        return repository.save(grade);
    }

    @Override
    public List<Grade> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteGradeById(Long id) {
        if(id == null){
            throw new RuntimeException("Nesne boş");
        }
        repository.deleteGradeById(id);
    }

    @Override
    public List<GradeDto> getGrades(Map<String,String> map ) {
        return repository.getGrades(Long.parseLong(map.get("lessonId")),
                Long.parseLong(map.get("studentId")),
                Long.parseLong(map.get("teacherId")));
    }
}
