package com.unevento.api.controllers;

import com.unevento.api.modelo.Usuario;
import com.unevento.api.records.NewUserRecord;
import com.unevento.api.records.UpdateUser;
import com.unevento.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



