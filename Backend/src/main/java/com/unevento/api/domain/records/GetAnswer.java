package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Usuario;

import java.util.Date;

public record GetAnswer(Long idrespuesta, Long idcomentario, GetUser getUser, Long idusuario, String contenido,
                        Date fecha) {
    public GetAnswer(com.unevento.api.domain.modelo.Respuesta respuesta, Usuario usuario) {
        this(
                respuesta.getIdrespuesta(),
                respuesta.getComentariorespuesta().getIdcomentario(),
                new GetUser(usuario),
                respuesta.getUsuariorespuesta().getIdUsuario(),
                respuesta.getComentarioTexto(),
                respuesta.getFecha()
        );

    }
}
