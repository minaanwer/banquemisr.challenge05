package com.banquemisr.challenge05.dto;

import com.banquemisr.challenge05.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private UserDto user;
    private String token;
}
