package com.dev.gestao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
@Log4j2
@RequiredArgsConstructor
public class NotificacaoService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    @Value(
            "${email.de}"
    )
    private String de;

    @Async
    public void enviar(String assunto, String mensagem, String para, String[] comCopia) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(assunto);
            message.setFrom(de);
            message.setTo(para);
            message.setText(mensagem);
            emailSender.send(message);
        } catch (Exception exception) {
           log.error(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }




    }

}
