package com.control2_backend.controller;

import com.control2_backend.entity.TareaEntity;
import com.control2_backend.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService service;

    @GetMapping
    public List<TareaEntity> getAll() {
        return service.getAllTareas();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody TareaEntity entity) {
        return service.addTarea(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaEntity> getById(@PathVariable Long id) {
        TareaEntity entity = service.getTareaById(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return service.deleteTarea(id);
    }
}