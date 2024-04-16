package com.unevento.api.controllers;

import com.unevento.api.controllers.services.ImageService;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.GetAllUserEvents;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/getuserevents")
public class GetUserEvents {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public GetUserEvents(EventRepository eventRepository, UserRepository userRepository, ImageService imageService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Page<GetAllUserEvents>> getUserEvents(@PageableDefault(size = 2) Pageable pageable, @PathVariable Long id) {
        Usuario usuario = userRepository.getById(id);
        Page<GetAllUserEvents> events = eventRepository.findByUsuarioCreador(usuario, pageable)
                .map(evento -> {
                    
                    String imageUrl = imageService.getImageName(evento.getImagen_path()); // Get image URI
                    return new GetAllUserEvents(evento, imageUrl);
                });

        return ResponseEntity.ok(events);
    }

}


