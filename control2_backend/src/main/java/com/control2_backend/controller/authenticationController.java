package com.control2_backend.controller;

import com.control2_backend.dto.login;
import com.control2_backend.dto.loginResponse;
import com.control2_backend.entity.UsuarioEntity;
import com.control2_backend.services.AuthenticationService;
import com.control2_backend.services.UsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class authenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,Integer>> login(@RequestBody login log, HttpServletResponse response){
        loginResponse Response = authenticationService.login(log);
        String token = Response.getToken();
        String cookieValue = "JWT=" + token + "; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=" + (60 * 60 * 24);
        HashMap<String, Integer> message = new HashMap<>();
        response.addHeader("Set-Cookie", cookieValue);
        message.put("id_user", Response.getIdUser());
        usuarioService.getTareasPorVencer(Response.getIdUser());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UsuarioEntity user) {
        return new ResponseEntity<>(authenticationService.addUsuario(user), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Boolean>> login(HttpServletResponse response){
        Cookie jwtCookie = new Cookie("JWT", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);

        response.addCookie(jwtCookie);

        HashMap<String, Boolean> message = new HashMap<>();
        message.put("success", true);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Boolean>> verifyToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        authenticationService.verify(cookies);
        HashMap<String, Boolean> message = new HashMap<>();
        message.put("success", true);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
