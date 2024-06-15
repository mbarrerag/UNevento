package com.unevento.api.controllers.services;

import com.unevento.api.domain.modelo.Comentario;
import com.unevento.api.domain.repository.CommentsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    private final CommentsRepository comentarioRepository;

    public ComentarioService(CommentsRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public Page<Comentario> findComentariosByEventoId(Long eventoId, Pageable pageable) {
        return comentarioRepository.findByIdeventocomentarios_Idevento(eventoId, pageable);
    }
}