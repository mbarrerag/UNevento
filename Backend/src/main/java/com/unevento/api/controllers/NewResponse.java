package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Comentario;
import com.unevento.api.domain.modelo.Respuesta;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.NewAnswer;
import com.unevento.api.domain.repository.AnswerRepository;
import com.unevento.api.domain.repository.CommentsRepository;
import com.unevento.api.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class NewResponse {

    private final AnswerRepository responseRepository;
    private final CommentsRepository commentsRepository;

    private final UserRepository userRepository;

    public NewResponse(AnswerRepository responseRepository, CommentsRepository commentsRepository, UserRepository userRepository) {
        this.responseRepository = responseRepository;
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> newResponse(@RequestBody NewAnswer newAnswer) {
        try {


            Comentario comentario = commentsRepository.findByIdcomentario(newAnswer.commentId());
            Usuario user = userRepository.findByIdUsuario(newAnswer.userId());
            if (comentario == null) {
                return ResponseEntity.badRequest().body("Comment not found");
            }
            responseRepository.save(new Respuesta(comentario, user, newAnswer.answer()));

            return ResponseEntity.ok("Response created");
        } catch (Exception ex) {
            System.out.println(ex);
            return ResponseEntity.status(500).build();
        }

    }
}