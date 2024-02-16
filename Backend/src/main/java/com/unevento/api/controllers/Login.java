package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.DataAuthentificationUser;
import com.unevento.api.domain.repository.UserRepository;
import com.unevento.api.infra.security.TokenAndUserId;
import com.unevento.api.infra.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

@RestController
@RequestMapping("/login")
public class Login {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public Login(AuthenticationManager authenticationManager, TokenService tokenService,  UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TokenAndUserId> login(@RequestBody DataAuthentificationUser dataAuthentificationUser) {
        try {
            // Authenticate user credentials
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(dataAuthentificationUser.correo(), dataAuthentificationUser.password());
            var userAuthentificated = authenticationManager.authenticate(authenticationToken);
            Usuario usuario = (Usuario) userRepository.findByCorreo(dataAuthentificationUser.correo());
            System.out.println(usuario.getNombre());
            // Generate RSA key pair
            KeyPair keyPair = TokenService.generateRSAKeyPair();

            // Generate RS256 token using the generated key pair
            String JWTtoken = tokenService.generateRS256Token((Usuario) userAuthentificated.getPrincipal(), keyPair);

            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            BigInteger modulus = publicKey.getModulus();
            System.out.println("Modulusaasda: " + modulus.toString());
            BigInteger exponent = publicKey.getPublicExponent();
            String modulusString = modulus.toString();
            String exponentString = exponent.toString();
            usuario.setPublickey(exponentString);  // Verify the generated token using the public key
            usuario.setModulo(modulusString);
            TokenAndUserId tokenAndUserId = new TokenAndUserId(JWTtoken, usuario.getIdUsuario());

            return ResponseEntity.ok(tokenAndUserId);
        } catch (Exception e) {
            throw new RuntimeException("Error during login", e);
        }
    }
}
