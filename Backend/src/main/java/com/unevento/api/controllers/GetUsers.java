package com.unevento.api.controllers;


import com.unevento.api.controllers.services.ImageService;
import com.unevento.api.domain.records.GetAllUsers;
import com.unevento.api.domain.repository.UserRepository;
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
    private final ImageService imageService;

    public GetUsers(UserRepository userRepository, ImageService imageService) {
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<Page<GetAllUsers>> getUsers(@PageableDefault(size = 2) Pageable pageable) {
        Page<GetAllUsers> users = userRepository.findAll(pageable).map(user -> {
            String imageUrl = imageService.getImageName((user.getImagen_path())); // Get image URL
            return new GetAllUsers(user, imageUrl);
        });
        return ResponseEntity.ok(users);
    }
}