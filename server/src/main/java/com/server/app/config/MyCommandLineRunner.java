package com.server.app.config;

import com.server.app.models.Grade;
import com.server.app.models.Lesson;
import com.server.app.models.Student;
import com.server.app.models.Teacher;
import com.server.app.repositories.GradeRepository;
import com.server.app.repositories.LessonRepository;
import com.server.app.repositories.StudentRepository;
import com.server.app.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyCommandLineRunner implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final LessonRepository  lessonRepository;
    private final GradeRepository   gradeRepository;

    @Override
    public void run(String... args) {
        Student student = Student.builder().name("oğuzhan").surname("demircioğlu").tckn("61135361398").build();
        studentRepository.save(student);
        Teacher teacher = Teacher.builder().name("şeyda").surname("kuşçu").tckn("11111111").build();
        teacherRepository.save(teacher);
        Lesson lesson   = Lesson.builder().countPerWeek(10).name("İngilizce").build();
        lessonRepository.save(lesson);
        Grade grade     = Grade.builder().teacher(teacher).student(student).lesson(lesson).gradeScore(61).build();
        gradeRepository.save(grade);

        student = Student.builder().name("ezgi").surname("ezgiiii").tckn("00000000000").build();
        studentRepository.save(student);
        teacher = Teacher.builder().name("ibrahim").surname("mahariq").tckn("222222222").build();
        teacherRepository.save(teacher);
        lesson  = Lesson.builder().countPerWeek(4).name("Fizik").build();
        lessonRepository.save(lesson);
        grade   = Grade.builder().teacher(teacher).student(student).lesson(lesson).gradeScore(100).build();
        gradeRepository.save(grade);
    }
}
