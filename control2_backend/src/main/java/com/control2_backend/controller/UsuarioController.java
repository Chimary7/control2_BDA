package com.control2_backend.controller;

import com.control2_backend.entity.UsuarioEntity;
import com.control2_backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioEntity> getAll() {
        return service.getAllUsuarios();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UsuarioEntity entity) {
        return service.addUsuario(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getById(@PathVariable Long id) {
        UsuarioEntity entity = service.getUsuarioById(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return service.deleteUsuario(id);
    }
}
