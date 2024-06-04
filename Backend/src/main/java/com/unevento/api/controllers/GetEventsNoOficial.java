package com.unevento.api.controllers;

import com.unevento.api.controllers.services.FileService;
import com.unevento.api.domain.modelo.Tipo;
import com.unevento.api.domain.records.GetAllEvenets;
import com.unevento.api.domain.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@CrossOrigin
@RequestMapping("/publicevents")
public class GetEventsNoOficial {

    private final EventRepository eventRepository;
    private final FileService fileService;

    public GetEventsNoOficial(EventRepository eventRepository, FileService fileService) {
        this.eventRepository = eventRepository;
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<Page<GetAllEvenets>> getEvents(@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) {

        try {
            LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
            Timestamp currentDate = Timestamp.valueOf(startOfDay);
            Page<GetAllEvenets> events = eventRepository.findByTipoAndFechaAfterOrEqual(Tipo.NO_OFICIAL, currentDate, pageable)
                    .map(evento -> {
                        String imageUrl = evento.getImagen_path(); // Get image URI
                        Long asistentesCount = (long) evento.getAsistentes().size(); // Calculate number of attendees
                        return new GetAllEvenets(evento, imageUrl, asistentesCount);
                    });
            return ResponseEntity.ok(events);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
