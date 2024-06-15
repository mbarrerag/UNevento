package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Comentario;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record GetAllComments(Long idComentario, String comentario, Date fecha, GetUser usuario, List<GetAllAnswers> respuestas) {
    public GetAllComments(Comentario comentario) {
        this(
                comentario.getIdcomentario(),
                comentario.getComentario(),
                comentario.getFecha(),
                new GetUser(comentario.getIdusuario()),
                comentario.getRespuestas().stream().map(GetAllAnswers::new).collect(Collectors.toList())
        );
    }
}
