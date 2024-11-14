package com.control2_backend.services;

import com.control2_backend.entity.AlertaEntity;
import com.control2_backend.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {
    private final AlertaRepository alertaRepository;

    @Autowired
    public AlertaService(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    public ResponseEntity<Object> addAlerta(AlertaEntity alerta) {
        try {
            alertaRepository.save(alerta);
            return new ResponseEntity<>("Se ingresó correctamente la categoría", HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo del error
            return new ResponseEntity<>("Error al ingresar la categoría: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<AlertaEntity> getAllAlertas() {
        return alertaRepository.findAll();
    }

    public AlertaEntity getAlertaById(long id) {
        return alertaRepository.findById(id);
    }

    public ResponseEntity<Object> deleteAlerta(long id) {
        AlertaEntity optionalAlerta = alertaRepository.findById(id);
        if (optionalAlerta != null){
            this.alertaRepository.deleteById(id);
            return new ResponseEntity<>("Se elimino correctamente la Categoria", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

