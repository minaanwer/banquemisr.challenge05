package com.banquemisr.challenge05.schedular;

import com.banquemisr.challenge05.model.Notification;
import com.banquemisr.challenge05.model.TaskEntity;
import com.banquemisr.challenge05.repo.NotificationRepository;
import com.banquemisr.challenge05.repo.TaskRepository;
import com.banquemisr.challenge05.util.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskSchedulerService {


    private final TaskRepository taskRepository;
    private final  NotificationRepository notificationRepository;
    private final EmailService emailService;


    @Scheduled(cron = "0,30 * * * * *")
    @Transactional
    public void checkUpcomingTasks() {
        LocalDate today = LocalDate.now();
        LocalDate thresholdDate = today.plusDays(1);  // Threshold for upcoming tasks (next 24 hours)

        // Fetch tasks that are due within the next 24 hours
        List<TaskEntity> upcomingTasks = taskRepository.findByDueDateBetween(today, thresholdDate);

        for (TaskEntity task : upcomingTasks) {
            // Check if the task already has a notification sent for it
            if (!isNotificationSentForTask(task)) {
                sendTaskNotification(task);
            }
        }
    }

    private boolean isNotificationSentForTask(TaskEntity task) {
        return notificationRepository.existsByUserAndMessage(task.getUser(), task.getTitle());
    }

    private void sendTaskNotification(TaskEntity task) {
        Notification notification = new Notification();
        notification.setMessage("Your task '" + task.getTitle() + "' is due on " + task.getDueDate());
        notification.setUser(task.getUser());
        notification.setReadStatus(false);
        notificationRepository.save(notification);

        emailService.sendEmail(task.getUser().getEmail(), "Task Reminder", notification.getMessage());
    }
}
