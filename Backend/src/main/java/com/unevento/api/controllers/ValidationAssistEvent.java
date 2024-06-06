package com.unevento.api.controllers;


import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.AnswerAssistEvent;
import com.unevento.api.domain.repository.AsistentRepository;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/validateEvent")
public class ValidationAssistEvent {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    private final AsistentRepository asistentRepository;

    public ValidationAssistEvent(UserRepository userRepository, EventRepository eventRepository, AsistentRepository asistentRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.asistentRepository = asistentRepository;
    }


    @PostMapping
    public ResponseEntity<com.unevento.api.domain.records.AnswerAssistEvent> ValidationAssistance(@RequestBody com.unevento.api.domain.records.ValidationAssistEvent validationAssistEvent) {
        try {


            Usuario usuario = userRepository.getById(validationAssistEvent.idUsuario());
            Eventos eventos = eventRepository.findByIdevento(validationAssistEvent.idEvento());
            AnswerAssistEvent answer = asistentRepository.findByUsuarioAndEvento(usuario, eventos) != null ? new AnswerAssistEvent(true) : new AnswerAssistEvent(false);
            return ResponseEntity.ok(answer);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}