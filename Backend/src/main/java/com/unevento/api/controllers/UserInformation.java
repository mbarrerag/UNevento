package com.unevento.api.controllers;

import com.unevento.api.controllers.services.ImageService;
import com.unevento.api.domain.modelo.Usuario;
import com.unevento.api.domain.records.GetInformation;
import com.unevento.api.domain.repository.UserRepository;
import com.unevento.api.infra.security.VerificationTokenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/userinformation")
public class UserInformation {

    private final UserRepository userRepository;
    private final VerificationTokenService tokenService;
    private final ImageService imageService;

    public UserInformation(UserRepository userRepository, VerificationTokenService tokenService, ImageService imageService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.imageService = imageService;
    }


    @PostMapping
    public ResponseEntity<com.unevento.api.domain.records.UserInformation> getPublicKey(@RequestBody GetInformation getInformation) {
        try {
            Usuario user = (Usuario) userRepository.findByCorreo(getInformation.email());
            String imageUrl = "as.png"; // Default image URL
            if (user.getImagen_path() != null) {
                imageUrl = imageService.getImageName(user.getImagen_path()); // Get image URL
            }
            return ResponseEntity.ok(new com.unevento.api.domain.records.UserInformation(
                    user.getIdUsuario(),
                    user.getUsername(),
                    user.getApellido(),
                    user.getCorreo(),
                    user.getRol(),
                    imageUrl
            ));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
