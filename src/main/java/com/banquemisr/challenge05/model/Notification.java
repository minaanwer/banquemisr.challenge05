package com.banquemisr.challenge05.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private boolean readStatus;

    @CreationTimestamp
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}