package com.unevento.api.controllers;


import com.unevento.api.domain.modelo.Comentario;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.GetAllCommentsWithUser;
import com.unevento.api.domain.repository.CommentsRepository;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class GetAllComments {

    private final CommentsRepository commentsRepository;
    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    public GetAllComments(CommentsRepository commentsRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.commentsRepository = commentsRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }


    //    private final AnswerRepository answerRepository;

    @GetMapping("/{id}")
    public Page<GetAllCommentsWithUser> getComments(@PathVariable Long id, @PageableDefault(size = 1) Pageable pageable, HttpServletRequest request) {
        Eventos eventos = eventRepository.findByIdevento(id);
        Page<Comentario> comentarios = commentsRepository.findByIdevento(eventos, pageable);

        return comentarios.map(comentario -> {
            Usuario usuario = userRepository.findByIdUsuario(comentario.getIdusuario().getIdUsuario()); // Suponiendo que tienes un repositorio de usuarios
            return new GetAllCommentsWithUser(comentario, usuario);
        });
    }

}