//package com.unevento.api.controllers;
//
//import com.unevento.api.domain.modelo.Asistente;
//import com.unevento.api.domain.modelo.Eventos;
//import com.unevento.api.domain.modelo.Usuario;
//import com.unevento.api.domain.records.AsistToEvents;
//import com.unevento.api.domain.repository.AsistentRepository;
//import com.unevento.api.domain.repository.EventRepository;
//import com.unevento.api.domain.repository.UserRepository;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/assistevent")
//
//public class AssistToAnEvent {
//
//   private final AsistentRepository asistentRepository;
//   private final UserRepository userRepository;
//   private final EventRepository eventRepository;
//
//    public AssistToAnEvent(AsistentRepository asistentRepository, UserRepository userRepository, EventRepository eventRepository) {
//        this.asistentRepository = asistentRepository;
//        this.userRepository = userRepository;
//        this.eventRepository = eventRepository;
//    }
//
//    @PostMapping
//    public void asistToAnEvent(@RequestBody AsistToEvents asistToEvents) {
//        Eventos eventos = eventRepository.findById_evento(asistToEvents.eventoid());
//        Usuario usuario = userRepository.findByIdUsuario(asistToEvents.idusuario());
//        Asistente asistente = asistentRepository.save(new Asistente(asistToEvents, eventos, usuario));
//    }
//}
