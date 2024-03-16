package com.unevento.api.controllers;

import com.unevento.api.domain.modelo.Eventos;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.NewEvent;
import com.unevento.api.domain.records.UpdateAnswerDataEvent;
import com.unevento.api.domain.repository.EventRepository;
import com.unevento.api.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/newevent")

public class NewEvents {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    public NewEvents(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }


    @PostMapping
    public ResponseEntity<UpdateAnswerDataEvent> creatingEvent(@RequestPart("newEvent") NewEvent newEvent, UriComponentsBuilder uriBuilder, @RequestPart("file") MultipartFile file) {
        Usuario user = userRepository.getById(newEvent.userID());

        Eventos eventos;
        try {
            String fileName = UUID.randomUUID().toString(); //ID único para el archivo
            String fileNameOrginal = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Long fileSize = file.getSize();
            Long maxFileSize = 5 * 1024 * 1024L;

            if (fileSize > maxFileSize) {
                throw new Exception("El archivo excede el tamaño permitido");
            } else if (fileSize == 0) {
                throw new Exception("El archivo no puede estar vacío");
            }
            if (!fileNameOrginal.endsWith(".jpg") && !fileNameOrginal.endsWith(".png") && !fileNameOrginal.endsWith(".jpeg")) {
                throw new Exception("El archivo no es una imagen");
            }
            String fileExtension = fileNameOrginal.substring(fileNameOrginal.lastIndexOf("."));
            String newFileName = fileName + fileExtension;
            File folder = new File("Backend/src/main/java/com/unevento/api/services/images");
            if (!folder.exists()) {
                System.out.println("Creando carpeta");
                folder.mkdirs();
            }

            Path path = Paths.get("Backend/src/main/java/com/unevento/api/services/images/" + newFileName);
            Files.write(path, bytes);

            eventos = eventRepository.save(new Eventos(newEvent, user, path.toString()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        UpdateAnswerDataEvent answer = new UpdateAnswerDataEvent(eventos.getId_evento(), eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(), eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(), eventos.getHora());
        URI uri = uriBuilder.path("/getevent/{id}").buildAndExpand(eventos.getId_evento()).toUri();
        return ResponseEntity.created(uri).body(answer);

    }

}
