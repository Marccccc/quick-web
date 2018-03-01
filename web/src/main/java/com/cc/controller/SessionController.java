package com.cc.controller;

import com.cc.com.service.AuthService;
import com.cc.com.service.UserService;
import com.cc.entity.User;
import com.cc.pack.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Token会话Controller
 * @author cyc
 */
@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    /**
     * 验证用户账户密码返回生成的token
     * @param user 组装一个User
     * @return 生成的JWT的Token
     */
    @PostMapping("/session")
    public Response login(@RequestBody User user) {
        return new Response().success(authService.login(user.getAccount(),user.getPassword()));
    }

}
