package com.Alura.Forum.Hub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}") // em minutos
    private String expiration;

    public String gerarToken(String email) {
        Algorithm algoritmo = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(email)
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
    }

    public String getSubject(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algoritmo).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject(); // retorna o email
        } catch (JWTVerificationException e) {
            return null; // token inválido ou expirado
        }
    }

    private Instant dataExpiracao() {
        int minutos = Integer.parseInt(expiration);
        return LocalDateTime.now().plusMinutes(minutos).toInstant(ZoneOffset.of("-03:00"));
    }
}