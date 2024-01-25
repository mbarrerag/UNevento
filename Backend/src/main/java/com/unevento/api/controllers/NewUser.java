package com.unevento.api.controllers;

import com.unevento.api.modelo.Usuario;
import com.unevento.api.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newuser")

public class NewUser {
    private final UserRepository userRepository;


    public NewUser(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @PostMapping
    public void newUser(@RequestBody com.unevento.api.records.NewUser dataUser) {
        userRepository.save(new Usuario(dataUser));
    }
}



