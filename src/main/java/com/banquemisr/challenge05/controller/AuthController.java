package com.banquemisr.challenge05.controller;

import com.banquemisr.challenge05.config.JwtTokenUtil;
import com.banquemisr.challenge05.dto.LoginRequest;
import com.banquemisr.challenge05.dto.LoginResponse;
import com.banquemisr.challenge05.mapper.UserMapper;
import com.banquemisr.challenge05.model.User;
import com.banquemisr.challenge05.service.CustomUserDetailsService;
import com.banquemisr.challenge05.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserMapper userMapper;

    @Autowired
    public AuthController(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, UserMapper userMapper) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            String token = jwtTokenUtil.generateToken(loginRequest.getUsername());

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
            if (userDetails == null) {
                return ResponseEntity.status(404).body(null);
            }
            LoginResponse loginResponse = new LoginResponse(userMapper.map(userDetails), token);
            return ResponseEntity.ok(loginResponse);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(null);
        }
    }
}
