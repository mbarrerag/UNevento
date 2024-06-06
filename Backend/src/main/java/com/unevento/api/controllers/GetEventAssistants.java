package com.unevento.api.controllers;

import com.unevento.api.domain.dto.AsistenteDto;
import com.unevento.api.domain.repository.AsistentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class GetEventAssistants {

    private final AsistentRepository asistentRepository;


    public GetEventAssistants(AsistentRepository asistentRepository) {
        this.asistentRepository = asistentRepository;
    }

    @GetMapping("/eventos/{eventoId}/asistentes")
    public List<AsistenteDto> getConfirmedAsistentes(@PathVariable Long eventoId) {
        try {


            return asistentRepository.findConfirmedAsistentesByEventoId(eventoId);

        } catch (EntityNotFoundException e) {
            return (List<AsistenteDto>) ResponseEntity.notFound().build();
        }
    }
}