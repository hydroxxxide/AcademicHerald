package com.example.academicherald.services;

import com.example.academicherald.entity.Event;
import com.example.academicherald.entity.Publication;
import com.example.academicherald.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    @Autowired
    public EmailService(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println(e.getMessage());
        }
    }
    public void sendEventPublicationMessage(Event event){
        if (!userRepository.findAllEmails().isEmpty()){
            for (String email :
                    userRepository.findAllEmails()) {
                String subject = "Новый ивент!";
                String text = "Доброго времени суток, совсем скоро новый ивент!\n\n" +
                        event.getTitle() +
                        "\n" +
                        event.getSubtitle();
                sendSimpleMessage(email, subject, text);
            }
        }
    }
    public void sendNewsPublicationMessage(Publication publication){
        if (!userRepository.findAllEmails().isEmpty()){
            for (String email:
                    userRepository.findAllEmails()) {
                String subject = "Новая новость";
                String text = "Доброго времени суток, у нас есть новая новость!\n\n" +
                        publication.getTitle() +
                        "\n" +
                        publication.getSubtitle();
                sendSimpleMessage(email, subject, text);
            }
        }
    }
    public void sendEventRemind(){

    }
}

