package com.banquemisr.challenge05.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HealthCheckController{

    @Value("${app.enabled}")
    private String appEnabled;


    @GetMapping("/checkHealth")
    public ResponseEntity<String> checkHealth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (appEnabled == null || appEnabled.isEmpty()) {
            return ResponseEntity.status(500).body("Missing required configuration: app.enabled");
        }

        if ("Y".equalsIgnoreCase(appEnabled)) {
            return ResponseEntity.ok("Server is up and running.");
        } else {
            return ResponseEntity.status(503).body("server is in maintenance mode.");
        }
    }
}