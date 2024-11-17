package com.control2_backend.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtil {
    private static final String SECRETKEY = "BDA Marin 2024";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRETKEY);

    public static String createToken(String name){
        return JWT.create()
                .withSubject(name)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 864000))
                .sign(algorithm);
    }

    public static DecodedJWT verifyToken(String token) throws JWTVerificationException{
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }


}
