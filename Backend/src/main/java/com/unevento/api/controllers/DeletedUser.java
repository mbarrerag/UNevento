package com.unevento.api.controllers;


import com.unevento.api.controllers.services.FileService;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/deletedUser/{id}")
public class DeletedUser {


    private final UserRepository userRepository;
    private final FileService fileService;

    public DeletedUser(UserRepository userRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.fileService = fileService;
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Usuario> deleteUser(@PathVariable Long id) throws IOException {
        try {
            Usuario user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

            // Delete user from the database
            userRepository.delete(user);

            // Delete profile picture file
            String image = user.getImagen_path();
            if (!image.equals("UserPhoto.jpg")) {
                System.out.println("Entre");
                fileService.delete(user.getImagen_path());
            }

            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
