package com.unevento.api.controllers;

import com.unevento.api.controllers.services.FileService;
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

    private final FileService fileService;

    public GetParticularEvent(EventRepository eventRepository, FileService fileService) {

        this.eventRepository = eventRepository;
        this.fileService = fileService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UpdateAnswerDataEvent> getParticularEvent(@PathVariable Long id) {
        try {
            Eventos evento = eventRepository.getById(id);
            String imageUrl = evento.getImagen_path(); // Get image URL
            return ResponseEntity.ok(new UpdateAnswerDataEvent(evento.getIdevento(), evento.getNombre(), evento
                    .getDescripcion(), evento.getLugar(), evento.getCategoria(), evento.getFacultad(), evento.getFecha_evento(), evento.getCapacidad(), evento.getHora(), imageUrl, evento.getTipo()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }
}
