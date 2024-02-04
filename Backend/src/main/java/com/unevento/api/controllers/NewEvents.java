package com.unevento.api.controllers;

import com.unevento.api.modelo.Eventos;
import com.unevento.api.modelo.Usuario;
import com.unevento.api.records.NewEvent;
import com.unevento.api.records.UpdateAnswerDataEvent;
import com.unevento.api.repository.EventRepository;
import com.unevento.api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/newevent")

public class NewEvents {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public NewEvents(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }



    @PostMapping
    public ResponseEntity<UpdateAnswerDataEvent> creatingEvent(@RequestBody NewEvent newEvent, UriComponentsBuilder uriBuilder) {
        Usuario user = userRepository.getById(newEvent.userID());
        Eventos eventos = eventRepository.save(new Eventos(newEvent, user));
        UpdateAnswerDataEvent answer = new UpdateAnswerDataEvent(eventos.getId_evento(), eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(), eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(), eventos.getHora());
        URI uri = uriBuilder.path("/getevent/{id}").buildAndExpand(eventos.getId_evento()).toUri();
        return ResponseEntity.created(uri).body(answer);
    }

}
