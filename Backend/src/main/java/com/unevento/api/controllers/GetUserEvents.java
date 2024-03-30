package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.GetAllUserEvents;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getuserevents")
public class GetUserEvents {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public GetUserEvents(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Page<GetAllUserEvents>> getUserEvents(@PageableDefault(size = 2) Pageable pageable, @PathVariable Long id) {
        Usuario usuario = userRepository.getById(id);
        Page<GetAllUserEvents> events = eventRepository.findByUsuarioCreador(usuario, pageable)
                .map(evento -> {
                    String imageUrl = getImageName(evento.getImagen_path()); // Get image URI
                    return new GetAllUserEvents(evento, imageUrl);
                });

        return ResponseEntity.ok(events);
    }


    private String getImageName(String imagePath) {
        if (imagePath == null) {
            return "9337d740-71a8-4ae5-8b68-df8110d68102.png"; // Default image name
        } else {
            int lastIndex = imagePath.lastIndexOf('/');
            if (lastIndex != -1) {
                return imagePath.substring(lastIndex + 1); // Extract the file name from the path
            } else {
                return imagePath; // If no path separator found, return the full path
            }
        }
    }
}


