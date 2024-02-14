package com.server.app.controllers;

import com.server.app.models.Grade;
import com.server.app.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/grade")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService service;

    @PostMapping(value = "/save")
    public ResponseEntity<Grade> save(@RequestBody Map<String,String> map) throws Exception {
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

    @PostMapping(value = "/deleteGradeById/{id}")
    public ResponseEntity<?> deleteGradeById(@PathVariable Long id){
        try {
            service.deleteGradeById(id);
            return ResponseEntity.ok().body("Kayıt Silindi");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("İşlem geçersiz");
        }
    }

    @PostMapping(value = "/getGrades")
    public ResponseEntity<?> getGrades(@RequestBody Map<String,String> map) throws Exception {
        try {
            return ResponseEntity.ok(service.getGrades(map));
        }catch (CommandAcceptanceException e){
            throw new CommandAcceptanceException(e.getMessage());
        }catch (Exception e){
            throw new Exception("işlem geçersiz");
        }
    }
}
