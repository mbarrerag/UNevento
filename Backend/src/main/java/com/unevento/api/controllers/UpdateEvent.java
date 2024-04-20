package com.unevento.api.controllers;

import com.unevento.api.controllers.services.FileDeletedService;
import com.unevento.api.controllers.services.FileUploadService;
import com.unevento.api.controllers.services.ImageService;
import com.unevento.api.domain.modelo.Categorias;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Facultades;
import com.unevento.api.domain.records.UpdateAnswerDataEvent;
import com.unevento.api.domain.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/updatetevent")
public class UpdateEvent {

    private final EventRepository eventRepository;
    private final com.unevento.api.controllers.services.FileUploadService fileUploadService;
    private final ImageService imageService;

    private final FileDeletedService fileDeletedService;

    public UpdateEvent(EventRepository eventRepository, FileUploadService fileUploadService, ImageService imageService, FileDeletedService fileDeletedService) {
        this.eventRepository = eventRepository;
        this.fileUploadService = fileUploadService;
        this.imageService = imageService;
        this.fileDeletedService = fileDeletedService;
    }

    @Transactional
    @CrossOrigin
    @PutMapping
    public ResponseEntity<UpdateAnswerDataEvent> updateEvent(@RequestPart("UpdateEvent") com.unevento.api.domain.records.UpdateEvent updateEvent, @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Eventos eventos = eventRepository.getReferenceById(updateEvent.id());
            eventos.setNombre(updateEvent.nombre());
            eventos.setDescripcion(updateEvent.descripcion());
            eventos.setFecha_evento(updateEvent.fechaEvento());
            eventos.setHora(updateEvent.hora());
            eventos.setLugar(updateEvent.lugar());
            eventos.setCapacidad(updateEvent.capacidad());
            eventos.setFacultad(Facultades.valueOf(updateEvent.Facultad()));
            eventos.setCategoria(Categorias.valueOf(updateEvent.categoria()));

            String imageUrl = null;

            if (file != null && !file.isEmpty()) {
                // If a new file is provided, delete the old one and upload the new one
                String oldImagePath = eventos.getImagen_path();
                if (oldImagePath != null) {
                    FileDeletedService.deleteFile(imageService.getImageName(oldImagePath));
                }
                imageUrl = FileUploadService.uploadFile(file); // Upload the new image and get the path
            } else {
                // If no new file is provided, keep the existing image path
                imageUrl = eventos.getImagen_path();
            }

            eventos.setImagen_path(imageUrl);

            // Guardar la entidad actualizada en la base de datos
            eventRepository.save(eventos);

            return ResponseEntity.ok(new UpdateAnswerDataEvent(eventos.getIdevento(), eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(), eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(), eventos.getHora(), eventos.getImagen_path(), eventos.getTipo()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
