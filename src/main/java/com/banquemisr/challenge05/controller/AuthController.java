package com.banquemisr.challenge05.controller;

import com.banquemisr.challenge05.config.JwtTokenUtil;
import com.banquemisr.challenge05.dto.LoginRequestDto;
import com.banquemisr.challenge05.dto.LoginResponseDto;
import com.banquemisr.challenge05.mapper.UserMapper;
import com.banquemisr.challenge05.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserMapper userMapper;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
            );

            String token = jwtTokenUtil.generateToken(loginRequestDto.getUsername());

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequestDto.getUsername());
            if (userDetails == null) {
                return ResponseEntity.status(404).body(null);
            }
            LoginResponseDto loginResponseDto = new LoginResponseDto(userMapper.map(userDetails), token);
            return ResponseEntity.ok(loginResponseDto);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(null);
        }
    }
}
