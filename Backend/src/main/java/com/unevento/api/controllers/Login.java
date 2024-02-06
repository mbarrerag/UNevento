package com.unevento.api.controllers;


import com.unevento.api.domain.records.DataAuthentificationUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class Login {

    private final AuthenticationManager authenticationManager;

    public Login(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @PostMapping
    public ResponseEntity login(DataAuthentificationUser dataAuthentificationUser){
        Authentication token = new UsernamePasswordAuthenticationToken(dataAuthentificationUser.username(), dataAuthentificationUser.password());
        System.out.println(token.getPrincipal());
        authenticationManager.authenticate(token);
        System.out.println(token.getPrincipal());
        return ResponseEntity.ok().build();
    }

    }

