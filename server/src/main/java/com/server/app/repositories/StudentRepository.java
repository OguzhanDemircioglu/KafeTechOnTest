package com.server.app.repositories;

import com.server.app.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Student getStudentByTckn(String tckn);

    Student getStudentById(Long id);

    boolean existsStudentByTckn(String tckn);

    void deleteStudentById(Long id);
}
