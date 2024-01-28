package com.unevento.api.controllers;

import com.unevento.api.modelo.Categorias;
import com.unevento.api.modelo.Eventos;
import com.unevento.api.modelo.Facultades;
import com.unevento.api.modelo.Usuario;
import com.unevento.api.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/updatetevent")
public class UpdateEvent {

    private final EventRepository eventRepository;

    public UpdateEvent(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @PutMapping
    public ResponseEntity<UpdateEvent> updateEvent(@RequestBody com.unevento.api.records.UpdateEvent updateEvent) {

        try {
            Eventos eventos = eventRepository.getReferenceById(updateEvent.id());
            eventos.setNombre(updateEvent.nombre());
            eventos.setDescripcion(updateEvent.descripcion());
            eventos.setFecha_evento(updateEvent.fechaEvento());
            eventos.setHora(updateEvent.hora());
            eventos.setLugar(updateEvent.lugar());
            eventos.setFacultad(Facultades.valueOf(updateEvent.Facultad()));
            eventos.setCategoria(Categorias.valueOf(updateEvent.categoria()));

            // Guardar la entidad actualizada en la base de datos
            eventRepository.save(eventos);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
