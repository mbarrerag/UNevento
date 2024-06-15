package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Respuesta;

public record GetAllAnswers(Long id, String texto, GetUser usuario) {
    public GetAllAnswers(Respuesta respuesta) {
        this(
                respuesta.getIdrespuesta(),
                respuesta.getComentarioTexto(),
                new GetUser(respuesta.getUsuariorespuesta())
        );
    }
}
