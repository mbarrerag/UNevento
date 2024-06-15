package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Comentario;
import com.unevento.api.domain.modelo.Usuario;

import java.util.Date;

public record GetAllCommentsWithUser(Long idComentario, String comentario, Date fecha, GetUser usuario) {
    public GetAllCommentsWithUser(Comentario comentario, Usuario usuario) {
        this(comentario.getIdcomentario(), comentario.getComentario(), comentario.getFecha(), new GetUser(usuario));
    }
}
