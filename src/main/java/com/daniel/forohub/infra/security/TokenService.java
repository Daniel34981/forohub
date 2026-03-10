package com.daniel.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${JWT_SECRET}")
    private String secret;

    public String generarToken(Authentication authentication) {
        return JWT.create()
                .withIssuer("ForoHub")
                .withSubject(authentication.getName())
                .withExpiresAt(fechaExpiracion())
                .sign(Algorithm.HMAC256(secret));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("ForoHub")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-05:00"));
    }
}