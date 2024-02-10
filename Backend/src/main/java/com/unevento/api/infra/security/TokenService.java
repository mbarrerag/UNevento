package com.unevento.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

public class TokenService {

    // Method to generate an RSA key pair
    private static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    // Method to generate JWT token with RS256 algorithm
    public static String generateRS256Token() {
        try {
            KeyPair keyPair = generateRSAKeyPair();
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());

            return JWT.create()
                    .withIssuer("UNevento")
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // Token expires in 1 hour
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("Error creating token", exception);
        }
    }

    // Method to verify JWT token with RS256 algorithm
    public static void verifyRS256Token(String token) {
        try {
            KeyPair keyPair = generateRSAKeyPair();
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("UNevento")
                    .build();
            verifier.verify(token);
            System.out.println("Token verification successful.");
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token verification failed", exception);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // Generate token
        String token = generateRS256Token();
        System.out.println("Generated token: " + token);

        // Verify token
        verifyRS256Token(token);
    }
}
