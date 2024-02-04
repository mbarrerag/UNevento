package com.unevento.api.controllers;

import com.unevento.api.records.GetAllEvenets;
import com.unevento.api.repository.EventRepository;
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
    public ResponseEntity<Page<GetAllEvenets>> getEvents(@PageableDefault(size = 2) Pageable pageable) {
        return ResponseEntity.ok(eventRepository.findByActivoTrue(pageable).map(GetAllEvenets::new));
    }
}
