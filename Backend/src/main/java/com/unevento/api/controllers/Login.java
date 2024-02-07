package com.unevento.api.controllers;


import com.unevento.api.domain.records.DataAuthentificationUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class Login {


    private final AuthenticationManager authenticationManager;

    public Login(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody DataAuthentificationUser dataAuthentificationUser){

        Authentication token = new UsernamePasswordAuthenticationToken(dataAuthentificationUser.username(), dataAuthentificationUser.password());

        authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

    }

