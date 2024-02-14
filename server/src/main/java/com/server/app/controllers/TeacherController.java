package com.server.app.controllers;

import com.server.app.models.Teacher;
import com.server.app.services.TeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService service;

    @PostMapping(value = "/save")
    public ResponseEntity<Teacher> save(@RequestBody Map<String,String> map) throws Exception {
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
    @DeleteMapping(value = "/deleteTeacherById/{id}")
    public ResponseEntity<?> deleteTeacherById(@PathVariable Long id){
        try {
            service.deleteTeacherById(id);
            return ResponseEntity.ok().body("Kayıt Silindi");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("İşlem geçersiz");
        }
    }

    @GetMapping(value = "/getTeachersByDetail")
    public ResponseEntity<?> getTeachersByDetail() throws Exception {
        try {
            return ResponseEntity.ok(service.getTeachersByDetail());
        }catch (Exception e){
            throw new Exception("işlem geçersiz");
        }
    }
}
