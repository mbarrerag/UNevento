package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.repository.AsistentRepository;
import com.unevento.api.domain.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@CrossOrigin
@RequestMapping("/deletedevent/{id}")
public class DeletedEvent {

    private final EventRepository eventRepository;
    private final AsistentRepository asistenteRepository;

    public DeletedEvent(EventRepository eventRepository, AsistentRepository asistenteRepository) {
        this.eventRepository = eventRepository;
        this.asistenteRepository = asistenteRepository;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteEvent(@PathVariable Long id) {
        Eventos event = eventRepository.getById(id);
        event.getAsistentes().forEach(asistente -> asistenteRepository.delete(asistente));
        eventRepository.delete(event);
        return ResponseEntity.noContent().build();
    }
}
