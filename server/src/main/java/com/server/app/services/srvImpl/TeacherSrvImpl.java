package com.server.app.services.srvImpl;

import com.server.app.models.Teacher;
import com.server.app.repositories.TeacherRepository;
import com.server.app.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeacherSrvImpl implements TeacherService {
    private final TeacherRepository repository;

    @Override
    public List<Teacher> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteTeacherByTckn(String tckn) {
        if(tckn == null){
            throw new RuntimeException("Nesne boş");
        }
        repository.deleteTeacherByTckn(tckn);
    }

    @Override
    public Teacher save(Map<String, String> map) {
        return null;
    }

}
