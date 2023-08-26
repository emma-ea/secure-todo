package com.emma_ea.secure_todo.mail_service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailSenderService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);

    private JavaMailSender sender;

    @Override
    @Async
    public void sendTo(String to, String subject, String message) {
        try {
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper mmHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mmHelper.setText(message, true);
            mmHelper.setTo(to);
            mmHelper.setSubject(subject);
            mmHelper.setFrom("noreply@localhost.com");
            sender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error(String.format("Failed to send email. %s",e.getMessage()));
        }
    }
}
