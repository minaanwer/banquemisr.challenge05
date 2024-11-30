package com.banquemisr.challenge05.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthCheckController {

    @Value("${app.enabled}")
    private String appEnabled;


    @GetMapping("/checkHealth")
    //@PreAuthorize("hasRole('USER_ROLE')")
    public ResponseEntity<String> checkHealth() {
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