package com.banquemisr.challenge05.service;

import com.banquemisr.challenge05.model.User;

public interface UserService {
    User findByUsername(String username);
}