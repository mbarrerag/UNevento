package com.unevento.api.infra.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Component
public class MailerManager {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public MailerManager(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String email, String code) {
        MimeMessage message = javaMailSender.createMimeMessage();
        String content = MesssageHTML.TEMPLATE_PRUEBA;


        try {
            message.setSubject("Código de confirmación");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);

            for (int i = 0; i < code.length(); i++) {
                content = setCodeInTemplate(content, i, String.valueOf(code.charAt(i)));
            }

            helper.setText(content, true);
            helper.setFrom(sender);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String setCodeInTemplate(String templateCode, int index, String number) {
        return templateCode.replace("{" + index + "}", number);
    }
}