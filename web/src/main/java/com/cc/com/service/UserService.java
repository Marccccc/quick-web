package com.cc.com.service;

import com.cc.entity.User;

public interface UserService {

    User getUser(int userId);

    User getUserByName(String name);
}
