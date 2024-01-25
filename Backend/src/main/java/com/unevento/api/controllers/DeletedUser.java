package com.unevento.api.controllers;


import com.unevento.api.modelo.Usuario;
import com.unevento.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
         return ResponseEntity.ok().build();
     }
}
