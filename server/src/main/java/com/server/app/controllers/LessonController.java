package com.server.app.controllers;

import com.server.app.models.Lesson;
import com.server.app.services.LessonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService service;

    @PostMapping(value = "/save")
    public ResponseEntity<Lesson> save(@RequestBody Map<String,String> map) throws Exception {
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
    @DeleteMapping(value = "/deleteLessonById/{id}")
    public ResponseEntity<?> deleteLessonById(@PathVariable Long id){
        try {
            service.deleteLessonById(id);
            return ResponseEntity.ok().body("Kayıt Silindi");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("İşlem geçersiz");
        }
    }
}
