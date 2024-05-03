package com.unevento.api.controllers;

import com.unevento.api.controllers.services.FileDeletedService;
import com.unevento.api.controllers.services.FileService;
import com.unevento.api.controllers.services.FileUploadService;
import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.NewEvent;
import com.unevento.api.domain.records.UpdateAnswerDataEvent;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.domain.repository.UserRepository;
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
    private final com.unevento.api.controllers.services.FileDeletedService fileDeletedService;

    private final com.unevento.api.controllers.services.FileUploadService fileUploadService;

    private final FileService fileService;

    public NewEvents(EventRepository eventRepository, UserRepository userRepository, FileUploadService fileUploadService, FileDeletedService fileDeletedService1, FileService fileService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;

        this.fileUploadService = fileUploadService;
        this.fileDeletedService = fileDeletedService1;
        this.fileService = fileService;
    }


    @PostMapping
    public ResponseEntity<UpdateAnswerDataEvent> creatingEvent(@RequestPart("newEvent") NewEvent newEvent, UriComponentsBuilder uriBuilder, @RequestPart(value = "file", required = false) MultipartFile file) {


        Usuario user = userRepository.getById(newEvent.userID());
        Eventos eventos;
        try {

            String nameimage;
            if (file == null || file.isEmpty()) {
                // If no file is provided or the file is empty, assign a default image path
                nameimage = "EventPhoto.JPG"; // Replace with your default image path
            } else {
                // Use FileUploadService to handle file upload and get the path
                //imagePath = fileUploadService.uploadFile(file);
                nameimage = fileService.upload(file);


            }

            eventos = eventRepository.save(new Eventos(newEvent, user, nameimage));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        UpdateAnswerDataEvent answer = new UpdateAnswerDataEvent(eventos.getIdevento(), eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(), eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(), eventos.getHora(), eventos.getImagen_path(), eventos.getTipo());
        URI uri = uriBuilder.path("/getevent/{id}").buildAndExpand(eventos.getIdevento()).toUri();
        return ResponseEntity.created(uri).body(answer);
    }
}
