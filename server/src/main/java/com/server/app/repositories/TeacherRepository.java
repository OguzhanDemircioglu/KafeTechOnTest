package com.server.app.repositories;

import com.server.app.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Teacher getTeacherByTckn(String tckn);

    void deleteTeacherByTckn(String tckn);

    boolean existsTeacherByTckn(String tckn);
}
