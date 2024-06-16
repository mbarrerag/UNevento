package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Comentario;
import com.unevento.api.domain.modelo.Respuesta;
import com.unevento.api.domain.modelo.Usuario;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record GetAllCommentsWithUserAndResponses(
        Long idComentario,
        String comentario,
        Date fecha,
        GetUser usuario,
        List<GetAnswer> respuestas
) {
    public GetAllCommentsWithUserAndResponses(Comentario comentario, Usuario usuario, List<Respuesta> respuestas) {
        this(
                comentario.getIdcomentario(),
                comentario.getComentario(),
                comentario.getFecha(),
                new GetUser(usuario),
                respuestas.stream().map(GetAnswer::new).collect(Collectors.toList())

        );
    }
}
