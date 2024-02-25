package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.UpdateAnswerDataUser;
import com.unevento.api.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/newuser")

public class NewUser {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public NewUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<UpdateAnswerDataUser> newUser(@RequestBody com.unevento.api.domain.records.NewUser dataUser, UriComponentsBuilder uriBuilder) {
      String encodedPassword = passwordEncoder.encode(dataUser.contrasena());
        Usuario user = new Usuario(dataUser);
        user.setPassword(encodedPassword);
        user = userRepository.save(user);
      UpdateAnswerDataUser answer = new UpdateAnswerDataUser(user.getIdUsuario(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getPassword());
      URI uri = uriBuilder.path("/getuser/{id}").buildAndExpand(user.getIdUsuario()).toUri();
      return ResponseEntity.created(uri).body(answer);
    }

   }




