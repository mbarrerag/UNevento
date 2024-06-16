package com.unevento.api.domain.records;

import java.util.Date;

public record GetAnswer(Long idrespuesta, Long idcomentario, Long idusuario, String contenido, Date fecha) {
    public GetAnswer(com.unevento.api.domain.modelo.Respuesta respuesta) {
        this(
                respuesta.getIdrespuesta(),
                respuesta.getComentariorespuesta().getIdcomentario(),
                respuesta.getUsuariorespuesta().getIdUsuario(),
                respuesta.getComentarioTexto(),
                respuesta.getFecha()
        );

    }
}
