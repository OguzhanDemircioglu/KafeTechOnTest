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
                map.get("teacherId") == null ||
                map.get("studentId") == null ||
                map.get("lessonId") == null
        ){
            throw new CommandAcceptanceException("Var olan Bir Nesne Tekrar Tanımlanamaz");
        }

        return repository.save(
                Grade.builder()
                        .gradeScore(Double.parseDouble(map.get("gradeNumber")))
                        .teacher(teacherRepository.getTeacherById(Long.parseLong(map.get("teacherId"))))
                        .student(studentRepository.getStudentById(Long.parseLong(map.get("studentId"))))
                        .lesson(lessonRepository.getLessonById(Long.parseLong(map.get("lessonId"))))
                        .build()
        );
    }

    @Override
    public List<Grade> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteGradeById(Long id) {
        repository.deleteGradeById(id);
    }

    @Override
    public List<GradeDto> getGrades(Map<String,String> map ) {
        return repository.getGrades(Long.parseLong(map.get("lessonId")),
                Long.parseLong(map.get("studentId")),
                Long.parseLong(map.get("teacherId")));
    }

    @Override
    public List<GradeDto> getAllGrades() {
        return repository.getAllGrades();
    }
}
