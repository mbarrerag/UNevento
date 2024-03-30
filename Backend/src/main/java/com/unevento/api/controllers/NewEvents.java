package com.unevento.api.controllers;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/newevent")

public class NewEvents {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    public NewEvents(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }


    @PostMapping
    public ResponseEntity<UpdateAnswerDataEvent> creatingEvent(@RequestPart("newEvent") NewEvent newEvent, UriComponentsBuilder uriBuilder, @RequestPart(value = "file", required = false) MultipartFile file) {
        Usuario user = userRepository.getById(newEvent.userID());
        Eventos eventos;
        try {
            String imagePath;
            if (file == null || file.isEmpty()) {
                // If no file is provided or the file is empty, assign a default image path
                imagePath = "Backend/src/main/resources/images/as.png"; // Replace with your default image path
            } else {
                // Save the uploaded file and get its path
                String fileName = UUID.randomUUID().toString(); // Unique ID for the file
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

                // Save the file to your desired location
                // Replace the path below with your actual file saving logic
                Path uploadedFilePath = Paths.get("Backend/src/main/resources/images/" + newFileName);
                Files.write(uploadedFilePath, bytes);
                imagePath = uploadedFilePath.toString();
            }

            eventos = eventRepository.save(new Eventos(newEvent, user, imagePath));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        UpdateAnswerDataEvent answer = new UpdateAnswerDataEvent(eventos.getIdevento(), eventos.getNombre(), eventos.getDescripcion(), eventos.getLugar(), eventos.getCategoria(), eventos.getFacultad(), eventos.getFecha_evento(), eventos.getCapacidad(), eventos.getHora());
        URI uri = uriBuilder.path("/getevent/{id}").buildAndExpand(eventos.getIdevento()).toUri();
        return ResponseEntity.created(uri).body(answer);
    }

}
