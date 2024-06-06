package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/findname")
public class FindUserByName {

    private final UserRepository userRepository;

    public FindUserByName(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<Usuario>> searchUsersByNameOrLastName(
            @PathVariable String query) { // Cambiado @RequestParam por @PathVariable
        try {
            System.out.println(query);
            List<Usuario> users = userRepository.findByNombreOrApellido(query);
            if (users.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(users);
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }
    }
}
