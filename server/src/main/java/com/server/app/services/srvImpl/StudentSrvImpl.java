package com.server.app.services.srvImpl;

import com.server.app.models.Student;
import com.server.app.repositories.StudentRepository;
import com.server.app.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentSrvImpl implements StudentService {
    private final StudentRepository repository;

    @Override
    public Student save(Map<String, String> map) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteStudentByTckn(String tckn) {
        if(tckn == null){
            throw new RuntimeException("Nesne boş");
        }
        repository.deleteStudentByTckn(tckn);
    }
}
