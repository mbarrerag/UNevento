package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.repository.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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
        return ResponseEntity.noContent().build();
    }
}
