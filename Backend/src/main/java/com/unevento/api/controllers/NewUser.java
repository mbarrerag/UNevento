package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.UpdateAnswerDataUser;
import com.unevento.api.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/newuser")

public class NewUser {
    private final UserRepository userRepository;


    public NewUser(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @PostMapping
    public ResponseEntity<UpdateAnswerDataUser> newUser(@RequestBody com.unevento.api.domain.records.NewUser dataUser, UriComponentsBuilder uriBuilder) {
      Usuario user = userRepository.save(new Usuario(dataUser));
      UpdateAnswerDataUser answer = new UpdateAnswerDataUser(user.getId(), user.getNombre(), user.getApellido(), user.getCorreo(), user.getPassword());
        URI uri = uriBuilder.path("/getuser/{id}").buildAndExpand(user.getId()).toUri();
      return ResponseEntity.created(uri).body(answer);
    }

   }




