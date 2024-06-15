package com.unevento.api.controllers;


import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.repository.CommentsRepository;
import com.unevento.api.domain.repository.EventRepository;
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

    public GetAllComments(CommentsRepository commentsRepository, EventRepository eventRepository) {
        this.commentsRepository = commentsRepository;
        this.eventRepository = eventRepository;
    }


    //    private final AnswerRepository answerRepository;


    @GetMapping("/{id}")
    public Page<com.unevento.api.domain.records.GetAllComments> getComments(@PathVariable Long id, @PageableDefault(size = 1) Pageable pageable, HttpServletRequest request) {

        Eventos eventos = eventRepository.findByIdevento(id);

        return commentsRepository.findByIdevento(eventos, pageable).map(com.unevento.api.domain.records.GetAllComments::new);
    }
}