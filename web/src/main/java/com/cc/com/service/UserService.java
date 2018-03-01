package com.cc.com.service;

import com.cc.entity.User;

public interface UserService {

    User getUser(int userId);


    int save(User user);

}
