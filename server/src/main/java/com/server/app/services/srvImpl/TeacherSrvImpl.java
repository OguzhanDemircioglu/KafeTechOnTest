package com.server.app.services.srvImpl;

import com.server.app.dtos.TeacherDetailDto;
import com.server.app.models.Grade;
import com.server.app.models.Student;
import com.server.app.models.Teacher;
import com.server.app.repositories.GradeRepository;
import com.server.app.repositories.TeacherRepository;
import com.server.app.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherSrvImpl implements TeacherService {
    private final TeacherRepository repository;
    private final GradeRepository gradeRepository;

    @Override
    public Teacher save(Map<String, String> map) {

        if(
                map.get("name") == null ||
                        map.get("surname") == null ||
                        map.get("tckn") == null ||
                        repository.existsTeacherByTckn(map.get("tckn"))
        ){
            throw new CommandAcceptanceException("Geçersiz işlem yada nesne kayıtlı");
        }

        return repository.save(
                Teacher.builder()
                        .name(map.get("name"))
                        .surname(map.get("surname"))
                        .tckn(map.get("tckn"))
                        .build()
        );
    }

    @Override
    public List<Teacher> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteTeacherById(Long id) {
        gradeRepository.deleteGradeByTeacherId(id);
        repository.deleteTeacherById(id);
    }

    @Override
    public List<TeacherDetailDto> getTeachersByDetail() {

        List<TeacherDetailDto> returnList = new ArrayList<>();

        for (Teacher t : repository.findAll()){
            TeacherDetailDto dto = new TeacherDetailDto();
            dto.setId(t.getId());
            dto.setName(t.getName());
            dto.setSurname(t.getSurname());
            dto.setTckn(t.getTckn());

            List<Grade> list = gradeRepository.getGradesByTeacherId(t.getId());
            dto.setStudentCount(
                    list.stream().map(Grade::getStudent).map(Student::getId).collect(Collectors.toSet()).size()
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
