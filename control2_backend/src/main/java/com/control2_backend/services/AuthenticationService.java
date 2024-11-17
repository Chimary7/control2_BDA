package com.control2_backend.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.control2_backend.dto.login;
import com.control2_backend.dto.loginResponse;
import com.control2_backend.entity.UsuarioEntity;
import com.control2_backend.jwt.JwtUtil;
import com.control2_backend.repository.UsuarioRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public loginResponse login(login log){
        UsuarioEntity usuarioEntity = usuarioRepository.findByName(log.getName());
        if (usuarioEntity == null){
            throw new IllegalStateException("The name or password is incorrect 1");
        }
        if (!log.getPassword().equals(usuarioEntity.getClave())){
            throw new IllegalStateException("The name or password is incorrect 2");
        }

        return loginResponse.builder()
            .token(JwtUtil.createToken(log.getName()))
                    .idUser(usuarioEntity.getId_usuario().intValue())
                    .build();

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

    public void verify(Cookie[] cookies){
        if (cookies != null){
            for (Cookie cookie: cookies){
                if (cookie.getName().equals("JWT")){
                    try{
                        String token = cookie.getValue();
                        DecodedJWT decodedJWT = JwtUtil.verifyToken(token);
                        return;
                    } catch (JWTVerificationException e){
                        throw new IllegalStateException("JWT invalid");
                    }
                }
            }
            throw new IllegalStateException("JWT not found");
        }
        throw new IllegalStateException("cookies not found");
    }

}
