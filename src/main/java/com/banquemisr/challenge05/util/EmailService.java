package com.banquemisr.challenge05.util;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String recipientEmail, String subject, String message) {

        System.out.println("Sending email to " + recipientEmail + " with subject: " + subject + " and message: " + message);
    }
}