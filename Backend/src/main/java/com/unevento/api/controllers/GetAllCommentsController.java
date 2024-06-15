package com.unevento.api.controllers;

import com.unevento.api.controllers.services.ComentarioService;
import com.unevento.api.domain.modelo.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class GetAllCommentsController {

    private final ComentarioService comentarioService;

    public GetAllCommentsController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping("/{eventoId}")
    public ResponseEntity<Page<Comentario>> getAllComments(@PathVariable Long eventoId, Pageable pageable) {
        Page<Comentario> comentarios = (Page<Comentario>) comentarioService.findComentariosByEventoId(eventoId, pageable);
        return ResponseEntity.ok(comentarios);
    }
}
