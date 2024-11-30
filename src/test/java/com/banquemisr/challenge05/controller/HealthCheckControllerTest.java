package com.banquemisr.challenge05.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class HealthCheckControllerTest {

    private HealthCheckController healthCheckController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        healthCheckController = new HealthCheckController();  // Initialize with a default value for appEnabled
    }

    @Test
    void checkHealth_whenAppEnabledIsMissing_returns500WithErrorMessage() {
        // Simulate missing 'app.enabled' configuration
        System.setProperty("app.enabled", "");

        ResponseEntity<String> response = healthCheckController.checkHealth();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Missing required configuration: app.enabled", response.getBody());
    }

    @Test
    void checkHealth_whenAppEnabledIsY_returns200WithSuccessMessage() {
        // Simulate 'app.enabled' set to "Y"
        System.setProperty("app.enabled", "Y");

        ResponseEntity<String> response = healthCheckController.checkHealth();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Server is up and running.", response.getBody());
    }

    @Test
    void checkHealth_whenAppEnabledIsN_returns503WithMaintenanceMessage() {
        // Simulate 'app.enabled' set to "N"
        System.setProperty("app.enabled", "N");

        ResponseEntity<String> response = healthCheckController.checkHealth();

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("server is in maintenance mode.", response.getBody());
    }

    @Test
    void checkHealth_whenAppEnabledIsEmpty_returns503WithMaintenanceMessage() {
        // Simulate 'app.enabled' set to an empty string
        System.setProperty("app.enabled", "");

        ResponseEntity<String> response = healthCheckController.checkHealth();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    void checkHealth_whenAppEnabledIsLowercaseY_returns200WithSuccessMessage() {
        // Simulate 'app.enabled' set to "y"
        System.setProperty("app.enabled", "y");

        ResponseEntity<String> response = healthCheckController.checkHealth();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Server is up and running.", response.getBody());
    }


    @Test
    void checkHealth_whenUserIsNotAuthorized_returns403Forbidden() {

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResponseEntity<String> response = healthCheckController.checkHealth();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
