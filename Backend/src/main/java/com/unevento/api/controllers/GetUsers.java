package com.unevento.api.controllers;


import com.unevento.api.controllers.services.FileService;
import com.unevento.api.domain.records.GetAllUsers;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/allusers")
public class GetUsers {

    private final UserRepository userRepository;
    private final FileService fileService;

    public GetUsers(UserRepository userRepository, FileService fileService, FileService fileService1) {
        this.userRepository = userRepository;
        this.fileService = fileService1;

    }

    @GetMapping
    public ResponseEntity<Page<GetAllUsers>> getUsers(@PageableDefault(size = 2) Pageable pageable) {
        try {
            Page<GetAllUsers> users = userRepository.findAll(pageable).map(user -> {
                String imageUrl = user.getImagen_path(); // Get image URL
                return new GetAllUsers(user, imageUrl);
            });
            return ResponseEntity.ok(users);
        } catch (
                EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }
}