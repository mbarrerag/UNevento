package com.unevento.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.unevento.api.domain.modelo.Usuario;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

// Asegúrate de incluir la dependencia de Bouncy Castle en tu proyecto
@Service
public class VerificationTokenService {


    public String verifyRS256Token(String token, Usuario usuario) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        DecodedJWT verifier;

        System.out.println("Entre");

        System.out.println("Usuario: " + usuario.getModulo() + " " + usuario.getPublickey());
        // Construir la clave pública RSA utilizando Bouncy Castle

        BigInteger modulo = new BigInteger(usuario.getModulo());
        System.out.println("newModulo: " + modulo);
        BigInteger publicKey = new BigInteger(usuario.getPublickey());
        System.out.println("newPublickey: " + publicKey);
        Security.addProvider(new BouncyCastleProvider());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
        RSAPublicKeySpec spec = new RSAPublicKeySpec(modulo, publicKey);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(spec);


        // Crear el algoritmo de verificación utilizando la clave pública construida
        Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, null); // No se necesita la clave privada para la verificación

        // Verificar el token JWT
        verifier = JWT.require(algorithm)
                .withIssuer("UNevento")
                .build()
                .verify(token);
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Invalid token");
        }
        System.out.println("Subject: " + verifier.getSubject());
        return verifier.getSubject();


    }

}

