package com.server.app.controllers;

import com.server.app.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService service;

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping(value = "/deleteTeacherByTckn/{tckn}")
    public ResponseEntity<?> deleteTeacherByTckn(@PathVariable String tckn){
        try {
            service.deleteTeacherByTckn(tckn);
            return ResponseEntity.ok().body("Kayıt Silindi");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("İşlem geçersiz");
        }
    }
}
