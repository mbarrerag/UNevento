package com.unevento.api.controllers;

import com.unevento.api.controllers.services.FileDeletedService;
import com.unevento.api.controllers.services.ImageService;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.repository.AsistentRepository;
import com.unevento.api.domain.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Transactional
@CrossOrigin
@RequestMapping("/deletedevent/{id}")
public class DeletedEvent {

    private final ImageService imageService;
    private final EventRepository eventRepository;
    private final AsistentRepository asistenteRepository;
    private final FileDeletedService FileDeletedService;


    public DeletedEvent(ImageService imageService, EventRepository eventRepository, AsistentRepository asistenteRepository, com.unevento.api.controllers.services.FileDeletedService fileDeletedService) {
        this.imageService = imageService;
        this.eventRepository = eventRepository;
        this.asistenteRepository = asistenteRepository;
        FileDeletedService = fileDeletedService;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteEvent(@PathVariable Long id) throws IOException {
        Eventos event = eventRepository.getById(id);
        event.getAsistentes().forEach(asistente -> asistenteRepository.delete(asistente));
        eventRepository.delete(event);
        String oldImagePath = event.getImagen_path();
        String image = imageService.getImageName(oldImagePath);

        if (!image.equals("EventoNoOficial.JPG") &&
                !image.equals("EventoNoOficial.JPG")) {

            try {
                FileDeletedService.deleteFile(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.noContent().build();
    }
}
