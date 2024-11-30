package com.banquemisr.challenge05.repo;

import com.banquemisr.challenge05.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquemisr.challenge05.model.Notification;
import com.banquemisr.challenge05.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    boolean existsByUserAndMessage(User user, String message);
}