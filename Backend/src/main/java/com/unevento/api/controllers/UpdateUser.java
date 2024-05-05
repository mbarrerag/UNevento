package com.unevento.api.controllers;

import com.unevento.api.controllers.services.FileService;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.UpdateAnswerDataUser;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Transactional
@CrossOrigin
@RequestMapping("/updateuser")
public class UpdateUser {

    public final UserRepository userRepository;

    private final FileService fileService;


    public UpdateUser(UserRepository userRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.fileService = fileService;

    }


    @Transactional
    @CrossOrigin
    @PutMapping
    public ResponseEntity<UpdateAnswerDataUser> updateUser(@RequestPart("userData") com.unevento.api.domain.records.UpdateUser dataUser, @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Usuario usuario = userRepository.getById(dataUser.id());
            // Actualizar los datos del usuario con los valores proporcionados en dataUser
            usuario.setNombre(dataUser.nombre());
            usuario.setApellido(dataUser.apellido());

            String profilePicturePath = "";

            if (file == null || file.isEmpty()) {
                // If no file is provided or the file is empty, keep the existing profile picture path
                profilePicturePath = usuario.getImagen_path();
            } else {
                fileService.delete(usuario.getImagen_path());
                profilePicturePath = fileService.upload(file);
            }
            usuario.setImagen_path(profilePicturePath);
            // Guardar la entidad actualizada en la base de datos
            System.out.println("Si hay nuevafoto profilePicturePath: " + profilePicturePath);
            userRepository.save(usuario);

            return ResponseEntity.ok(new UpdateAnswerDataUser(usuario.getIdUsuario(), usuario.getCorreo(), usuario.getNombre(), usuario.getApellido(), usuario.getImagen_path()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}