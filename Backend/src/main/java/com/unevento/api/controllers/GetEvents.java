package com.unevento.api.controllers;

import com.unevento.api.controllers.services.FileService;
import com.unevento.api.domain.modelo.Facultades;
import com.unevento.api.domain.modelo.Tipo;
import com.unevento.api.domain.records.GetAllEvenets;
import com.unevento.api.domain.repository.EventRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/home/{faculties}")
public class GetEvents {

    private final EventRepository eventRepository;
    private final FileService fileService;

    public GetEvents(EventRepository eventRepository, FileService fileService) {
        this.eventRepository = eventRepository;

        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<Page<GetAllEvenets>> getEvents(@PathVariable Facultades faculties, @PageableDefault Pageable pageable, HttpServletRequest request) {
        Page<GetAllEvenets> events = eventRepository.findByFacultadAndTipo(faculties, Tipo.OFICIAL, pageable)
                .map(evento -> {
                    String imageUrl = evento.getImagen_path();
                    System.out.println("Entresss");
                    return new GetAllEvenets(evento, imageUrl);
                });
        return ResponseEntity.ok(events);
    }

}
