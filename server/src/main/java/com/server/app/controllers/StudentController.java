package com.server.app.controllers;

import com.server.app.models.Student;
import com.server.app.services.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @PostMapping(value = "/save")
    public ResponseEntity<Student> save(@RequestBody Map<String,String> map) throws Exception {
        try {
            return ResponseEntity.ok(service.save(map));
        }catch (CommandAcceptanceException e){
            throw new CommandAcceptanceException(e.getMessage());
        }catch (Exception e){
            throw new Exception("işlem geçersiz");
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Transactional
    @DeleteMapping(value = "/deleteStudentById/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id){
        try {
            service.deleteStudentById(id);
            return ResponseEntity.ok().body("Kayıt Silindi");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("İşlem geçersiz");
        }
    }
}
