package com.marllonmendez.voll.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.marllonmendez.voll.modal.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class Token {

    @Value("${api.security.token.secret}")
    private String token;

    public String gerarToken(Usuario usuario) {
        try {
            var alg = Algorithm.HMAC256(token);
            return JWT.create()
                    .withIssuer("API Voll")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(datatoken())
                    .sign(alg);
        } catch (JWTCreationException exceptiont) {
            throw new RuntimeException("Erro ao gerar token", exceptiont);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var alg = Algorithm.HMAC256(token);
            return JWT.require(alg)
                    .withIssuer("API Voll")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTCreationException exceptiont) {
            throw new RuntimeException("Token JWT invalido ou expirado", exceptiont);
        }
    }

    private Instant datatoken() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }

}
