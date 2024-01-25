package com.unevento.api.controllers;

import com.unevento.api.modelo.Usuario;
import com.unevento.api.records.NewUserRecord;
import com.unevento.api.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newuser")

public class NewUser {

    private final UserRepository userRepository;

    public NewUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public void newUser(@RequestBody NewUserRecord dataUser) {
        userRepository.save(new Usuario(dataUser));
    }
}
