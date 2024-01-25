package com.unevento.api.controllers;

import com.unevento.api.modelo.Eventos;
import com.unevento.api.repository.EventRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newevent")

public class NewEvent {
    private final EventRepository eventRepository;

    public NewEvent(EventRepository eventRepository) {
        this.eventRepository = eventRepository;

    }

    @PostMapping
    public void newEvent(@RequestBody NewEvent newEvent) {
        eventRepository.save(new Eventos(newEvent));
    }

}
