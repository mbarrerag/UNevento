package com.unevento.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
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

    // Método para obtener la clave pública en formato PKCS #1
    private static String getPKCS1Format(RSAPublicKey publicKey) {
        String pkcs1Header = "-----BEGIN RSA PUBLIC KEY-----\n";
        String pkcs1Footer = "-----END RSA PUBLIC KEY-----";
        String base64EncodedKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        return pkcs1Header + base64EncodedKey + pkcs1Footer;
    }

    // Método para obtener la clave pública en formato X.509 Certificate
    private static String getX509Format(RSAPublicKey publicKey) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            return Base64.getEncoder().encodeToString(x509EncodedKeySpec.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException("Error encoding public key to X.509 format", e);
        }
    }

    // Método para obtener la clave pública en formato JWK
    private static String getJWKFormat(RSAPublicKey publicKey) {
        String modulus = Base64.getUrlEncoder().withoutPadding().encodeToString(publicKey.getModulus().toByteArray());
        String exponent = Base64.getUrlEncoder().withoutPadding().encodeToString(publicKey.getPublicExponent().toByteArray());
        return "{\"kty\":\"RSA\",\"n\":\"" + modulus + "\",\"e\":\"" + exponent + "\"}";
    }
}