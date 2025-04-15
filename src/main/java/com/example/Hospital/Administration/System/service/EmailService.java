package com.example.Hospital.Administration.System.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yourhospital@example.com"); // Must match your spring.mail.username
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
}

    }

