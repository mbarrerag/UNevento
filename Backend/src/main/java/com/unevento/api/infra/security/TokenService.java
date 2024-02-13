package com.unevento.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.unevento.api.domain.modelo.Usuario;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class TokenService {

    // Method to generate an RSA key pair
    public static KeyPair generateRSAKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate RSA key pair", e);
        }
    }

    // Method to generate JWT token with RS256 algorithm
    public String generateRS256Token(Usuario usuario, KeyPair keyPair) {
        try {
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            System.out.println("Public key: " + publicKey);
            System.out.println("Private key: " + privateKey);



            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

            return JWT.create()
                    .withIssuer("UNevento")
                    .withExpiresAt(getExpirationTime()) // Token expires in 1 hour
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("Error creating token", exception);
        }

        }

    public  Instant getExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }

    // Method to verify JWT token with RS256 algorithm
    public static void verifyRS256Token(String token, RSAPublicKey publicKey) {
        DecodedJWT verifier;
        try {

            Algorithm algorithm = Algorithm.RSA256(publicKey, null); // No se necesita la clave privada para la verificación
             verifier = JWT.require(algorithm)
                    .withIssuer("UNevento")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token verification failed", exception);
        }
    }
}