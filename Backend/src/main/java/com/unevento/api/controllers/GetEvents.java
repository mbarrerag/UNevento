package com.unevento.api.controllers;

import com.unevento.api.domain.records.GetAllEvenets;
import com.unevento.api.domain.repository.EventRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class GetEvents {

    private final EventRepository eventRepository;

    public GetEvents(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public ResponseEntity<Page<GetAllEvenets>> getEvents(@PageableDefault(size = 2) Pageable pageable, HttpServletRequest request) {
        Page<GetAllEvenets> events = eventRepository.findByActivoTrue(pageable)
                .map(evento -> {
                    String imageUrl = getImageName(evento.getImagen_path()); // Get image URI
                    return new GetAllEvenets(evento, imageUrl);
                });
        return ResponseEntity.ok(events);
    }

    // Method to get image URI

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
