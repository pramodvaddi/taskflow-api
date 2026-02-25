package com.pramodvaddiraju.taskflow.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendTaskCreatedEmail(String to, String title) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Task created successfully");
        message.setText("Your task titles " + title + " has been created successfully");
        javaMailSender.send(message);

    }

    @Override
    public void sendTaskCompletedEmail(String to, String title) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Task Completed");
        message.setText("Congratulations! Your task " + title + " has been completed successfully");
        javaMailSender.send(message);

    }
}
