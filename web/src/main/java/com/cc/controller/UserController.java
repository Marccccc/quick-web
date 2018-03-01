package com.cc.controller;

import com.cc.com.service.UserService;
import com.cc.pack.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostAuthorize("hasRole('user_Admin')")
    @GetMapping("/user/{id}")
    public Response hello(@PathVariable("id")Integer userId) {
        return new Response().success(userService.getUser(userId));
    }

}
