package com.server.app.services.srvImpl;

import com.server.app.dtos.GradeDetailDto;
import com.server.app.dtos.GradeDto;
import com.server.app.dtos.LessonDetailDto;
import com.server.app.models.Grade;
import com.server.app.models.Lesson;
import com.server.app.models.Student;
import com.server.app.repositories.GradeRepository;
import com.server.app.repositories.LessonRepository;
import com.server.app.repositories.StudentRepository;
import com.server.app.repositories.TeacherRepository;
import com.server.app.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<GradeDetailDto> getGradesByDetail() {

        List<GradeDetailDto> returnList = new ArrayList<>();

        for (Grade t : repository.findAll()){
            GradeDetailDto dto = new GradeDetailDto();
            dto.setId(t.getId());

            dto.setGradeScore(t.getGradeScore());
            dto.setLessonName(t.getLesson().getName());

            dto.setStudentName(t.getStudent().getName());
            dto.setStudentSurname(t.getStudent().getSurname());

            dto.setTeacherName(t.getTeacher().getName());
            dto.setTeacherSurname(t.getTeacher().getSurname());

            dto.setPersonalSuccessPercentage(
                    t.getGradeScore()
            );
            dto.setClassSuccessPercentage(
                    t.getLesson().getGrades().stream().mapToDouble(Grade::getGradeScore)
                            .average().orElse(0.0)
            );

            if(dto.getPersonalSuccessPercentage()>90){
                dto.setNote("AA");
            } else if (dto.getPersonalSuccessPercentage()>80) {
                dto.setNote("BB");
            } else if (dto.getPersonalSuccessPercentage()>70) {
                dto.setNote("CC");
            }else if (dto.getPersonalSuccessPercentage()>60) {
                dto.setNote("DD");
            }else if (dto.getPersonalSuccessPercentage()>50) {
                dto.setNote("DC");
            }else {
                dto.setNote("FF");
            }

            returnList.add(dto);
        }
        return returnList;
    }
}
