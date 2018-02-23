package com.cc.controller;

import com.cc.pack.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {

    @GetMapping("/hello")
    public Response hello(String name) {
        return new Response().success("name:"+name);
    }

}
