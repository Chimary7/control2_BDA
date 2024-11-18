package com.control2_backend.services;

import com.control2_backend.entity.TareaEntity;
import com.control2_backend.repository.TareaRepository;
import com.control2_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public TareaService(TareaRepository tareaRepository, UsuarioRepository usuarioRepository) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
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

    public ResponseEntity<Object> updateTareaToFinalizada(long id) {
        try {
            TareaEntity tarea = tareaRepository.findById(id);

            if (tarea == null) {
                return new ResponseEntity<>("Tarea no encontrada", HttpStatus.NOT_FOUND);
            }

            Long usuarioId = tarea.getId_usuario();
            List<TareaEntity> tareas_a_vencer = usuarioRepository.verificarTareasPorVencer(usuarioId);
            boolean tareaProximaAVencer = tareas_a_vencer.stream()
                    .anyMatch(t -> Objects.equals(t.getId_tarea(), tarea.getId_tarea()));

            if (tarea.getEstado().equals("Pendiente") || tareaProximaAVencer) {
                tarea.setEstado("Completada");
                tareaRepository.save(tarea);
                return new ResponseEntity<>("Estado de la tarea actualizado a completada", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("La tarea ya se encuentra completada", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            // Si ocurre un error, retornar el mensaje de error
            return new ResponseEntity<>("Error al actualizar la tarea: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteTarea(long id) {
        TareaEntity optionalTarea = tareaRepository.findById(id);
        if (optionalTarea != null) {
            this.tareaRepository.deleteById(id);
            return new ResponseEntity<>("Se elimino correctamente la Categoria", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public List<TareaEntity> searchTareas(String estado, String keyword) {
        return tareaRepository.findByEstadoAndKeyword(estado, keyword);
    }

}
