package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.UpdateAnswerDataUser;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
            return ResponseEntity.ok(new UpdateAnswerDataUser(user.getId(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getPassword()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
