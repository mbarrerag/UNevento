package com.unevento.api.controllers;

import com.unevento.api.modelo.Eventos;
import com.unevento.api.repository.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deletedevent/{id}")
public class DeletedEvent {

    private final EventRepository eventRepository;

    public DeletedEvent(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteEvent(@PathVariable Long id) {
        Eventos event = eventRepository.getById(id);
        eventRepository.delete(event);
        return ResponseEntity.ok().build();
    }
}
