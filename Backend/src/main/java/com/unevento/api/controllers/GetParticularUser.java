package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.UpdateAnswerDataUser;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/getuser")
public class GetParticularUser {

    private final UserRepository userRepository;

    public GetParticularUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateAnswerDataUser> getParticularEvent(@PathVariable Long id) {
        try {
            Usuario user = userRepository.getById(id);
            return ResponseEntity.ok(new UpdateAnswerDataUser(user.getIdUsuario(), user.getCorreo(), user.getNombre(), user.getApellido()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
