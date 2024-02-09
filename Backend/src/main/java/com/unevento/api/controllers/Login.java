package com.unevento.api.controllers;


import com.unevento.api.domain.records.DataAuthentificationUser;
import com.unevento.api.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class Login {


    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;
    public Login(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody DataAuthentificationUser dataAuthentificationUser){

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(dataAuthentificationUser.username(), dataAuthentificationUser.password());

        authenticationManager.authenticate(authenticationToken);
        var JWTtoken  = tokenService.generateToken();
        return ResponseEntity.ok(JWTtoken);
    }

    }

