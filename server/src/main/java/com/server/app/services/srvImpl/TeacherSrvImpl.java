package com.server.app.services.srvImpl;

import com.server.app.models.Student;
import com.server.app.models.Teacher;
import com.server.app.repositories.GradeRepository;
import com.server.app.repositories.TeacherRepository;
import com.server.app.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
}
