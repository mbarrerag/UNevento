package com.unevento.api.controllers;


import com.unevento.api.controllers.services.FileDeletedService;
import com.unevento.api.controllers.services.ImageService;
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
    private final FileDeletedService fileDeletedService;
    private final ImageService imageService;

    public DeletedUser(UserRepository userRepository, FileDeletedService fileDeletedService, ImageService imageService) {
        this.userRepository = userRepository;
        this.fileDeletedService = fileDeletedService;
        this.imageService = imageService;
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
            String image = imageService.getImageName(user.getImagen_path());
            if (!image.equals("EventoNoOficial.JPG") &&
                    !image.equals("EventoNoOficial.JPG")) {

                try {
                    FileDeletedService.deleteFile(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
