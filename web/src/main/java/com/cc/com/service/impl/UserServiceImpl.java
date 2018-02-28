package com.cc.com.service.impl;

import com.cc.com.service.UserService;
import com.cc.dao.UserDao;
import com.cc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.getUserByName(username);
        if(user==null) {
            new UsernameNotFoundException("未找到此用户");
        }
        return user;
    }
}
