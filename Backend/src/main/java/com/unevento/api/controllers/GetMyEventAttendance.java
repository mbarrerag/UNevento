package com.unevento.api.controllers;

import com.unevento.api.domain.records.GetAllEvenets;
import com.unevento.api.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class GetMyEventAttendance {

    private final EventRepository eventRepository;

    @Autowired
    public GetMyEventAttendance(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/MisAsistencias/{asistenteId}")
    public ResponseEntity<Page<GetAllEvenets>> getEventsByAsistente(@PathVariable Long asistenteId, @PageableDefault(size = 10) Pageable pageable) {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        Timestamp currentDate = Timestamp.valueOf(startOfDay);
        Page<GetAllEvenets> events = eventRepository.findEventsByUsuarioIdAndFechaAfterOrEqual(asistenteId, currentDate, pageable);
        return ResponseEntity.ok(events);
    }
}