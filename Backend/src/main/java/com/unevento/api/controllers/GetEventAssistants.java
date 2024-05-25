package com.unevento.api.controllers;

import com.unevento.api.domain.dto.AsistenteDto;
import com.unevento.api.domain.repository.AsistentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetEventAssistants {

    private final AsistentRepository asistentRepository;

    @Autowired
    public GetEventAssistants(AsistentRepository asistentRepository) {
        this.asistentRepository = asistentRepository;
    }

    @GetMapping("/eventos/{eventoId}/asistentes")
    public List<AsistenteDto> getConfirmedAsistentes(@PathVariable Long eventoId) {
        return asistentRepository.findConfirmedAsistentesByEventoId(eventoId);
    }
}