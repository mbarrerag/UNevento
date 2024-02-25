package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Asistente;
import com.unevento.api.domain.records.AsistToEvents;
import com.unevento.api.domain.repository.AsistentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assistevent")

public class AssistToAnEvent {

   private final AsistentRepository asistentRepository;

    public AssistToAnEvent(com.unevento.api.domain.repository.AsistentRepository asistentRepository) {
        this.asistentRepository = asistentRepository;

    }

    @PostMapping
    public void asistToAnEvent(@RequestBody AsistToEvents asistToEvents) {
        Asistente asistente = asistentRepository.save(new Asistente(asistToEvents.idusuario(), asistToEvents.estado(), asistToEvents.eventoid())
    }
}
