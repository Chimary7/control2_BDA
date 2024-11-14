package com.control2_backend.controller;

import com.control2_backend.entity.AlertaEntity;
import com.control2_backend.services.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerta")
public class AlertaController {

    @Autowired
    private AlertaService service;

    @GetMapping
    public List<AlertaEntity> getAll() {
        return service.getAllAlertas();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody AlertaEntity entity) {
        return service.addAlerta(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaEntity> getById(@PathVariable Long id) {
        AlertaEntity entity = service.getAlertaById(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return service.deleteAlerta(id);
    }
}