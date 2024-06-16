package com.unevento.api.domain.records;

import com.unevento.api.domain.modelo.Comentario;
import com.unevento.api.domain.modelo.Respuesta;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.repository.UserRepository;

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
    public GetAllCommentsWithUserAndResponses(Comentario comentario, Usuario usuario, List<Respuesta> respuestas, UserRepository userRepository) {
        this(
                comentario.getIdcomentario(),
                comentario.getComentario(),
                comentario.getFecha(),
                new GetUser(usuario),
                respuestas.stream().map(respuesta -> {
                    Usuario respuestaUsuario = userRepository.findByIdUsuario(respuesta.getUsuariorespuesta().getIdUsuario());
                    return new GetAnswer(respuesta, respuestaUsuario);
                }).collect(Collectors.toList())
        );
    }
}
