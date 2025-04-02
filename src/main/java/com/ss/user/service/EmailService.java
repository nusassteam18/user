package com.ss.user.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {
    private final JavaMailSender mailSend;

    public EmailService(JavaMailSender mailSend) {
        this.mailSend = mailSend;
    }

    public void forgotPasswordEmail(String email){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setText("Click link to reset password");
        msg.setSubject("Reset Password");
        mailSend.send(msg);
    }
}
