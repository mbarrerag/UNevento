package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.records.UpdateAnswerDataEvent;
import com.unevento.api.domain.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/getevent")
public class GetParticularEvent {


    private final EventRepository eventRepository;

    public GetParticularEvent(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UpdateAnswerDataEvent> getParticularEvent(@PathVariable Long id) {
        try {
            Eventos evento = eventRepository.getById(id);
            return ResponseEntity.ok(new UpdateAnswerDataEvent(evento.getIdevento(), evento.getNombre(), evento
                    .getDescripcion(), evento.getLugar(), evento.getCategoria(), evento.getFacultad(), evento.getFecha_evento(), evento.getCapacidad(), evento.getHora()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
