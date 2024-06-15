package com.unevento.api.controllers;

import com.unevento.api.controllers.services.ComentarioService;
import com.unevento.api.domain.modelo.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<Page<Comentario>> getComentariosByEventoId(@PathVariable Long eventoId, Pageable pageable) {
        Page<Comentario> comentarios = comentarioService.findComentariosByEventoId(eventoId, pageable);
        return ResponseEntity.ok(comentarios);
    }
}
