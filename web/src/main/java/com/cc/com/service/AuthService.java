package com.cc.com.service;

import com.cc.entity.User;

/**
 * 权限服务
 * @author cyc
 */
public interface AuthService {

    /**
     * 用户登录
     * @param account 用户账户
     * @param password 用户密码
     * @return
     */
    String login(String account, String password);

}
