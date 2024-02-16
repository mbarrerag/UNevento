package com.unevento.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.repository.UserRepository;

import java.security.interfaces.RSAPublicKey;


public class VerificationToken {

    private final UserRepository userRepository;
    public VerificationToken(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String verifyRS256Token(Long id, String token) {

        DecodedJWT verifier;
        try {
            Usuario usuario = userRepository.getById(id);
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) usuario.getPublickey(), null); // No se necesita la clave privada para la verificación
            verifier = JWT.require(algorithm)
                    .withIssuer("UNevento")
                    .build()
                    .verify(token);
            System.out.println("Token verified" + verifier.getSubject() + " " + verifier.getClaim("id").asInt());

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token verification failed", exception);
        }
        return verifier.getSubject();
    }


}
