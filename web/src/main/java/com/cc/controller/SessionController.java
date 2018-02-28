package com.cc.controller;

import com.cc.com.service.AuthService;
import com.cc.com.service.UserService;
import com.cc.entity.User;
import com.cc.pack.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @GetMapping("/session/{id}")
    public Response hello(@PathVariable("id")Integer userId) {
        User user=userService.getUser(userId);
        return new Response().success(authService.login(user.getName(),user.getPassword()));
    }

}
