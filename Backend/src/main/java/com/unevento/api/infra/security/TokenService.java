package com.unevento.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

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
    public static String generateRS256Token(KeyPair keyPair) {
        try {
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("Public key: " + publicKey);
            System.out.println("Private key: " + privateKey);
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

            return JWT.create()
                    .withIssuer("UNevento")
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // Token expires in 1 hour
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("Error creating token", exception);
        }
    }

    // Method to verify JWT token with RS256 algorithm
    public static void verifyRS256Token(String token, RSAPublicKey publicKey) {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, null); // No se necesita la clave privada para la verificación
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("UNevento")
                    .build();
            verifier.verify(token);

            System.out.println("Token verification successful.");
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token verification failed", exception);
        }
    }
}