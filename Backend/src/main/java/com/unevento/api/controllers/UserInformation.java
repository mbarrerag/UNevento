package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.GetInformation;
import com.unevento.api.domain.repository.UserRepository;
import com.unevento.api.infra.security.VerificationTokenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publickeys")
public class UserInformation {

    private final UserRepository userRepository;
    private final VerificationTokenService tokenService;


    public UserInformation(UserRepository userRepository, VerificationTokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }


    @PostMapping
    public ResponseEntity<com.unevento.api.domain.records.UserInformation> getPublicKey(@RequestBody GetInformation getInformation) {
        try {

            Usuario user = (Usuario) userRepository.findByCorreo(getInformation.email());

            return ResponseEntity.ok(new com.unevento.api.domain.records.UserInformation(user.getIdUsuario(), user.getUsername(), user.getApellido(), user.getCorreo(), user.getRol()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
