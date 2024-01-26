package com.unevento.api.controllers;

import com.unevento.api.modelo.Eventos;
import com.unevento.api.modelo.Usuario;
import com.unevento.api.records.NewEvent;
import com.unevento.api.repository.EventRepository;
import com.unevento.api.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newevent")

public class NewEventController {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public NewEventController(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public void creatingEvent(@RequestBody NewEvent newEvent) {
        Usuario user = userRepository.getById(newEvent.userID());
        System.out.println("user: " + user.getNombre());
        eventRepository.save(new Eventos(newEvent, user));
    }

}
