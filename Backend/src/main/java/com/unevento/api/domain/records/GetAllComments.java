package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Comentario;

import java.util.Date;

public record GetAllComments(Long idComentario, String comentario, Date fecha) {
    public GetAllComments(Comentario comentario) {
        this(comentario.getIdcomentario(), comentario.getComentario(), comentario.getFecha());
    }
}
