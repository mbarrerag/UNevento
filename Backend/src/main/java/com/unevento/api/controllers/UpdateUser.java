package com.unevento.api.controllers;

import com.unevento.api.modelo.Usuario;
import com.unevento.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updateUser")
public class UpdateUser {

    public final UserRepository userRepository;

    public UpdateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    @PutMapping
    public ResponseEntity<com.unevento.api.records.UpdateUser> updateUser(@RequestBody com.unevento.api.records.UpdateUser dataUser) {
        System.out.println("update user"+dataUser.id()+dataUser.nombre()+dataUser.apellido()+dataUser.correo()+dataUser.contrasena());
        try {
            Usuario usuario = userRepository.getById(dataUser.id());
            // Actualizar los datos del usuario con los valores proporcionados en dataUser
            usuario.setNombre(dataUser.nombre());
            usuario.setApellido(dataUser.apellido());
            usuario.setCorreo(dataUser.correo());
            usuario.setPassword(dataUser.contrasena());

            // Guardar la entidad actualizada en la base de datos
            userRepository.save(usuario);

            return ResponseEntity.ok(new com.unevento.api.records.UpdateUser(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getPassword()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}

