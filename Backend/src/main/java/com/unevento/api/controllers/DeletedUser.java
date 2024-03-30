package com.unevento.api.controllers;


import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/deletedUser/{id}")
public class DeletedUser {


     private final UserRepository userRepository;

     public DeletedUser(UserRepository userRepository) {
         this.userRepository = userRepository;
     }

     @DeleteMapping
     @Transactional
     public ResponseEntity<Usuario> deleteUser(@PathVariable Long id) {
         Usuario user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
         userRepository.delete(user);
         return ResponseEntity.noContent().build();
     }
}
