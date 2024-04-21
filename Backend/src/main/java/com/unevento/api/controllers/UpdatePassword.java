package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.UpdatePasswordResponse;
import com.unevento.api.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/updatepassword")
public class UpdatePassword {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdatePassword(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PutMapping
    public ResponseEntity<UpdatePasswordResponse> updatePassword(@RequestBody com.unevento.api.domain.records.UpdatePassword updatePassword) {

        Usuario user = (Usuario) userRepository.findByCorreo(updatePassword.correo());
        String encodedPassword = passwordEncoder.encode(updatePassword.newPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return ResponseEntity.ok(new UpdatePasswordResponse(user.getCorreo(), user.getPassword()));
    }
}
