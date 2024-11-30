package com.banquemisr.challenge05;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "P@ssw0rd";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("encodedPassword :"+ encodedPassword);
    }
}