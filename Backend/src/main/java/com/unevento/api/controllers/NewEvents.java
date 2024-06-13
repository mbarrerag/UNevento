package com.unevento.api.controllers;

import com.unevento.api.controllers.services.BadWordsHandler.ContentFilterService;
import com.unevento.api.controllers.services.FileService;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.NewEvent;
import com.unevento.api.domain.records.UpdateAnswerDataEvent;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/neweventun")
public class NewEvents {
    private static final Logger logger = Logger.getLogger(NewEvents.class.getName());
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final ContentFilterService contentFilterService;


    public NewEvents(EventRepository eventRepository, UserRepository userRepository, FileService fileService, ContentFilterService contentFilterService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.fileService = fileService;
        this.contentFilterService = contentFilterService;
    }

    @PostMapping
    public ResponseEntity<UpdateAnswerDataEvent> creatingEvent(@RequestPart("newEvent") NewEvent newEvent, UriComponentsBuilder uriBuilder, @RequestPart(value = "file", required = false) MultipartFile file) {
        try {


            // Verificar si el nombre o la descripción contienen palabras prohibidas
            if (contentFilterService.containsBadWords(newEvent.nombre()) || contentFilterService.containsBadWords(newEvent.descripcion()) || contentFilterService.containsBadWords(newEvent.lugar())) {
                System.out.println("Bad Words");
                return ResponseEntity.badRequest().body(null);

            }

            Usuario user = userRepository.getById(newEvent.userID());
            Eventos eventos;
            try {
                String nameimage;
                if (file == null || file.isEmpty()) {
                    nameimage = "EventosOficial.JPG"; // Reemplazar con tu ruta de imagen predeterminada
                } else {
                    nameimage = fileService.upload(file);
                }

                eventos = eventRepository.save(new Eventos(newEvent, user, nameimage));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            UpdateAnswerDataEvent answer = new UpdateAnswerDataEvent(eventos.getIdevento(), eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(), eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(), eventos.getHora(), eventos.getImagen_path(), eventos.getTipo());
            URI uri = uriBuilder.path("/getevent/{id}").buildAndExpand(eventos.getIdevento()).toUri();
            return ResponseEntity.created(uri).body(answer);
        } catch (
                EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}