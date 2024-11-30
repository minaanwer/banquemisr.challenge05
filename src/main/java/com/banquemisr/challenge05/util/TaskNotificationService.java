package com.banquemisr.challenge05.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskNotificationService {

    private final EmailService emailService;

    public void notifyUser(String email, String taskTitle, String dueDate) {
        String subject = "Task Reminder: " + taskTitle;
        String message = "Your task '" + taskTitle + "' is due on " + dueDate + ". Please take necessary actions.";
        emailService.sendEmail(email, subject, message);
    }
}