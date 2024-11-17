package com.control2_backend.jwt;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JWT")) {
                    try {
                        String token = cookie.getValue();
                        DecodedJWT decodedJWT = JwtUtil.verifyToken(token);

                        String name = decodedJWT.getSubject();

                        if (name != null) {
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(name, null, new ArrayList<>());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    } catch (JWTVerificationException e) {
                        System.out.println("Invalid JWT");
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}
