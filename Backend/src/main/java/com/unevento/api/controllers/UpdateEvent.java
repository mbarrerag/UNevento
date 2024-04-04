package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Categorias;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Facultades;
import com.unevento.api.domain.records.UpdateAnswerDataEvent;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.services.FileUploadService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/updatetevent")
public class UpdateEvent {

    private final EventRepository eventRepository;
    private final com.unevento.api.services.FileUploadService fileUploadService;

    public UpdateEvent(EventRepository eventRepository, FileUploadService fileUploadService) {
        this.eventRepository = eventRepository;
        this.fileUploadService = fileUploadService;
    }


    @PutMapping
    public ResponseEntity<UpdateAnswerDataEvent> updateEvent(@RequestPart("UpdateEvent") com.unevento.api.domain.records.UpdateEvent updateEvent, @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Eventos eventos = eventRepository.getReferenceById(updateEvent.id());
            eventos.setNombre(updateEvent.nombre());
            eventos.setDescripcion(updateEvent.descripcion());
            eventos.setFecha_evento(updateEvent.fechaEvento());
            eventos.setHora(updateEvent.hora());
            eventos.setLugar(updateEvent.lugar());
            eventos.setFacultad(Facultades.valueOf(updateEvent.Facultad()));
            eventos.setCategoria(Categorias.valueOf(updateEvent.categoria()));

            String imagePath;
            if (file == null || file.isEmpty()) {
                // If no file is provided or the file is empty, assign a default image path
                imagePath = "Backend/src/main/resources/images/as.png"; // Replace with your default image path
            } else {
                // Use FileUploadService to handle file upload and get the path
                imagePath = fileUploadService.uploadFile(file);
            }
            eventos.setImagen_path(imagePath);

            // Guardar la entidad actualizada en la base de datos
            eventRepository.save(eventos);

            return ResponseEntity.ok(new UpdateAnswerDataEvent(eventos.getIdevento(), eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(), eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(), eventos.getHora(), eventos.getImagen_path()));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
