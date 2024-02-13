package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.DataAuthentificationUser;
import com.unevento.api.infra.security.DataJWTToken;
import com.unevento.api.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

@RestController
@RequestMapping("/login")
public class Login {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public Login(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DataJWTToken> login(@RequestBody DataAuthentificationUser dataAuthentificationUser) {
        try {
            // Authenticate user credentials
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(dataAuthentificationUser.username(), dataAuthentificationUser.password());
            var usuerAuthentificated = authenticationManager.authenticate(authenticationToken);

            // Generate RSA key pair
            KeyPair keyPair = TokenService.generateRSAKeyPair();

            // Generate RS256 token using the generated key pair
            String JWTtoken = tokenService.generateRS256Token((Usuario) usuerAuthentificated.getPrincipal(), keyPair);

            // Verify the generated token using the public key
            tokenService.verifyRS256Token(JWTtoken, (RSAPublicKey) keyPair.getPublic());

            return ResponseEntity.ok(new DataJWTToken(JWTtoken));
        } catch (Exception e) {
            throw new RuntimeException("Error during login", e);
        }
    }
}
