package com.server.app.repositories;

import com.server.app.dtos.GradeDto;
import com.server.app.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {

    @Query("Select new com.server.app.dtos.GradeDto (g.id, g.gradeScore, g.lesson.name, g.student.name, g.student.surname,g.teacher.name, g.teacher.surname )" +
            "from Grade g " +
            "where g.lesson.id = :lessonId " +
            "or g.student.id = :studentId " +
            "or g.teacher.id = :teacherId " +
            "order by g.gradeScore desc" )
    List<GradeDto> getGrades(@Param("lessonId") long lessonId,
                                       @Param("studentId") long studentId,
                                       @Param("teacherId") long teacherId);

    @Query("Select new com.server.app.dtos.GradeDto (g.id, g.gradeScore, g.lesson.name, g.student.name, g.student.surname,g.teacher.name, g.teacher.surname )" +
            "from Grade g " +
            "order by g.id asc " )
    List<GradeDto> getAllGrades();

    List<Grade> getGradesByTeacherId(Long id);

    List<Grade> getGradesByStudentId(Long id);

    List<Grade> getGradesByLessonId(Long id);

    void deleteGradeById(Long id);

    void deleteGradeByStudentId(Long id);

    void deleteGradeByTeacherId(Long id);

    void deleteGradeByLessonId(Long id);

}
