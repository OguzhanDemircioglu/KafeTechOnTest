package com.server.app.services.srvImpl;

import com.server.app.dtos.StudentDetailDto;
import com.server.app.dtos.TeacherDetailDto;
import com.server.app.models.Grade;
import com.server.app.models.Student;
import com.server.app.models.Teacher;
import com.server.app.repositories.GradeRepository;
import com.server.app.repositories.StudentRepository;
import com.server.app.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentSrvImpl implements StudentService {
    private final StudentRepository repository;
    private final GradeRepository gradeRepository;

    @Override
    public Student save(Map<String, String> map) {

        if(
                        map.get("name") == null ||
                        map.get("surname") == null ||
                        map.get("tckn") == null ||
                        repository.existsStudentByTckn(map.get("tckn"))
        ){
            throw new CommandAcceptanceException("Geçersiz işlem yada nesne kayıtlı");
        }

        return repository.save(
                Student.builder()
                        .name(map.get("name"))
                        .surname(map.get("surname"))
                        .tckn(map.get("tckn"))
                        .build()
        );
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteStudentById(Long id) {
        gradeRepository.deleteGradeByStudentId(id);
        repository.deleteStudentById(id);
    }

    @Override
    public List<StudentDetailDto> getStudentsByDetail() {

        List<StudentDetailDto> returnList = new ArrayList<>();

        for (Student t : repository.findAll()){
            StudentDetailDto dto = new StudentDetailDto();
            dto.setId(t.getId());
            dto.setName(t.getName());
            dto.setSurname(t.getSurname());
            dto.setTckn(t.getTckn());

            List<Grade> list = gradeRepository.getGradesByStudentId(t.getId());
            dto.setLessonCount(
                    list.stream().map(Grade::getLesson).collect(Collectors.toSet()).size()
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
