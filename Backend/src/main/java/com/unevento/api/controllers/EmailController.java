package com.unevento.api.controllers;

import com.unevento.api.domain.records.ConfirmationEmail;
import com.unevento.api.infra.email.MailerManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/sendCode")
public class EmailController {

    private final MailerManager mailerManager;

    public EmailController(MailerManager mailerManager) {
        this.mailerManager = mailerManager;
    }

    @PostMapping
    public ResponseEntity<String> sendCode(@RequestBody ConfirmationEmail confirmationEmail) {
        System.out.println("Entre");
        String email = confirmationEmail.email(); // Obtener la dirección de correo electrónico del objeto ConfirmationEmail
        // Generar un código de 6 dígitos
        String code = generateSixDigitCode();
        // Enviar el código por correo electrónico
        mailerManager.sendMessage(email, code);

        // Retornar el código generado
        return ResponseEntity.ok(code);
    }

    private String generateSixDigitCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}