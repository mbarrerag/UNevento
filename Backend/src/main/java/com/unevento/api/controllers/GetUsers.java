package com.unevento.api.controllers;


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

    public GetUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<Page<GetAllUsers>> getUsers(@PageableDefault(size = 2) Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(pageable).map(GetAllUsers::new));
    }
}
