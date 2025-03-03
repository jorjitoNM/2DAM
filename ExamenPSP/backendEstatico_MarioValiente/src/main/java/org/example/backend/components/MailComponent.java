package org.example.backend.components;

import lombok.extern.log4j.Log4j2;
import org.example.backend.common.Constantes;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class MailComponent {
    private final JavaMailSender javaMailSender;

    public MailComponent(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        try {
            javaMailSender.send(message);
            return true;
        } catch (MailException e) {
            log.error(Constantes.ERROR_AL_ENVIAR_EL_CORREO, e.getMessage(), e);
            return false;
        }
    }
}
