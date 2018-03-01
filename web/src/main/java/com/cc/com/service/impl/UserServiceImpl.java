package com.cc.com.service.impl;

import com.cc.com.service.UserService;
import com.cc.dao.UserDao;
import com.cc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public int save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userAccount) {
        User user = userDao.getUserByAccount(userAccount);
        if (user == null) {
            throw new UsernameNotFoundException("未找到此用户");
        }
        return user;
    }

}
