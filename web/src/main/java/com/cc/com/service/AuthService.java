package com.cc.com.service;

import com.cc.entity.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
