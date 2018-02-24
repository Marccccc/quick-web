package com.cc.com.service.impl;

import com.cc.com.service.UserService;
import com.cc.dao.UserDao;
import com.cc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(int userId) {
        return userDao.getUserById(userId);
    }
}
