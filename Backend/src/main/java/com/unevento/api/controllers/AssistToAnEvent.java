package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Asistente;
import com.unevento.api.domain.modelo.Boleto;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.AsistToEvents;
import com.unevento.api.domain.repository.AsistentRepository;
import com.unevento.api.domain.repository.BoletoRepository;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/assistevent")

public class AssistToAnEvent {

    private final AsistentRepository asistentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final BoletoRepository boletoRepository;

    public AssistToAnEvent(AsistentRepository asistentRepository, UserRepository userRepository, EventRepository eventRepository, BoletoRepository boletoRepository) {
        this.asistentRepository = asistentRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.boletoRepository = boletoRepository;
    }

    @PostMapping
    public void asistToAnEvent(@RequestBody AsistToEvents asistToEvents) {

        try {
            Eventos eventos = eventRepository.findByIdevento(asistToEvents.eventoid());
            Usuario usuario = userRepository.findByIdUsuario(asistToEvents.idusuario());
            Boleto boleto = new Boleto(usuario.getNombre());
            boleto = boletoRepository.save(boleto);
            System.out.println("aa" + boleto.getNombre_usuario() + boleto.getId_boleto());

            Asistente asistente = asistentRepository.save(new Asistente(asistToEvents, eventos, usuario, boleto));
        } catch (EntityNotFoundException exception) {
            ResponseEntity.notFound().build();
        }
    }
}
