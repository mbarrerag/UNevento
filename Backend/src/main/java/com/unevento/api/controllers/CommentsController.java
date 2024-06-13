package com.unevento.api.controllers;

import com.unevento.api.controllers.services.BadWordsHandler.ContentFilterService;
import com.unevento.api.domain.modelo.Comentario;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.AddComments;
import com.unevento.api.domain.records.AnswerNewCommnet;
import com.unevento.api.domain.repository.CommentsRepository;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@CrossOrigin
@RequestMapping("/addcomment")
public class CommentsController {

    private final CommentsRepository commentsRepository;
    private final ContentFilterService contentFilterService;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public CommentsController(CommentsRepository commentsRepository, ContentFilterService contentFilterService, EventRepository eventRepository, UserRepository userRepository) {
        this.commentsRepository = commentsRepository;
        this.contentFilterService = contentFilterService;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<AnswerNewCommnet> addNewComment(@RequestBody AddComments addComments) {
        try {
            Usuario usuario = userRepository.findByIdUsuario(addComments.id_usuario());
            Eventos eventos = eventRepository.findByIdevento(addComments.id_evento());
            Comentario comentario = new Comentario(usuario, eventos, addComments);
            commentsRepository.save(comentario);

            // Asumimos que AnswerNewCommnet tiene un constructor que acepta un mensaje
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
