package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Asistente;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.repository.AsistentRepository;
import com.unevento.api.domain.repository.BoletoRepository;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deletedassistance")
public class DeletedAssistance {

    private final AsistentRepository asistentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final BoletoRepository boletoRepository;

    public DeletedAssistance(AsistentRepository asistentRepository, UserRepository userRepository, EventRepository eventRepository, BoletoRepository boletoRepository) {
        this.asistentRepository = asistentRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.boletoRepository = boletoRepository;
    }

    @PostMapping
    public ResponseEntity<Object> annulAssistance(@RequestBody com.unevento.api.domain.records.DeletedAssistance deletedAssistance) {

        Usuario usuario = userRepository.findByIdUsuario(deletedAssistance.idusuario());
        Eventos eventos = eventRepository.findByIdevento(deletedAssistance.idusuario());
        try {
            Asistente asistente = asistentRepository.findByUsuarioAndEvento(usuario, eventos);
            asistentRepository.delete(asistente);

        } catch (EntityNotFoundException e) {
            System.out.println(e);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAssistance(@RequestBody com.unevento.api.domain.records.DeletedAssistance deletedAssistance) {
        Usuario usuario = userRepository.findByIdUsuario(deletedAssistance.idusuario());
        Eventos eventos = eventRepository.findByIdevento(deletedAssistance.eventoid());

        try {
            Asistente asistente = asistentRepository.findByUsuarioAndEvento(usuario, eventos);
            if (asistente != null) {
                asistentRepository.delete(asistente);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
