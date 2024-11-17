package com.control2_backend.services;

import com.control2_backend.entity.TareaEntity;
import com.control2_backend.entity.UsuarioEntity;
import com.control2_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<Object> addUsuario(UsuarioEntity cliente) {
        try {
            usuarioRepository.save(cliente);
            return new ResponseEntity<>("Se ingresó correctamente la categoría", HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo del error
            return new ResponseEntity<>("Error al ingresar la categoría: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<UsuarioEntity> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity getUsuarioById(long id) {
        return usuarioRepository.findById(id);
    }

    public ResponseEntity<Object> deleteUsuario(long id) {
        UsuarioEntity optionalCliente = usuarioRepository.findById(id);
        if (optionalCliente != null){
            this.usuarioRepository.deleteById(id);
            return new ResponseEntity<>("Se elimino correctamente la Categoria", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public List<TareaEntity> getTareasPorVencer(long id) {
        return usuarioRepository.verificarTareasPorVencer(id);
    }
}
