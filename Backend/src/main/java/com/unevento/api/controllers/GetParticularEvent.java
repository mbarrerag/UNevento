package com.unevento.api.controllers;

import com.unevento.api.controllers.services.ImageService;
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

    private final ImageService imageService;
    private final EventRepository eventRepository;

    public GetParticularEvent(ImageService imageService, EventRepository eventRepository) {
        this.imageService = imageService;
        this.eventRepository = eventRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UpdateAnswerDataEvent> getParticularEvent(@PathVariable Long id) {
        try {
            Eventos evento = eventRepository.getById(id);
            String imageUrl = imageService.getImageName(evento.getImagen_path()); // Get image URL
            return ResponseEntity.ok(new UpdateAnswerDataEvent(evento.getIdevento(), evento.getNombre(), evento
                    .getDescripcion(), evento.getLugar(), evento.getCategoria(), evento.getFacultad(), evento.getFecha_evento(), evento.getCapacidad(), evento.getHora(), imageUrl, evento.getTipo()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }
}
