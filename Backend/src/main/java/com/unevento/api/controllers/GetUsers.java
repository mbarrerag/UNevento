package com.unevento.api.controllers;


import com.unevento.api.modelo.Usuario;
import com.unevento.api.records.GetAllUsers;
import com.unevento.api.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/allusers")
public class GetUsers {

    private final UserRepository userRepository;


    public GetUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Page<GetAllUsers> getUsers(@PageableDefault(size = 2) Pageable pageable) {
        return userRepository.findAll(pageable).map(GetAllUsers::new);
    }
}
