package com.control2_backend.services;

import com.control2_backend.entity.TareaEntity;
import com.control2_backend.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;

    @Autowired
    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public ResponseEntity<Object> addTarea(TareaEntity tarea) {
        try {
            tareaRepository.save(tarea);
            return new ResponseEntity<>("Se ingresó correctamente la categoría", HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo del error
            return new ResponseEntity<>("Error al ingresar la categoría: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<TareaEntity> getAllTareas() {
        return tareaRepository.findAll();
    }

    public TareaEntity getTareaById(long id) {
        return tareaRepository.findById(id);
    }

    public ResponseEntity<Object> deleteTarea(long id) {
        TareaEntity optionalTarea = tareaRepository.findById(id);
        if (optionalTarea != null){
            this.tareaRepository.deleteById(id);
            return new ResponseEntity<>("Se elimino correctamente la Categoria", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
