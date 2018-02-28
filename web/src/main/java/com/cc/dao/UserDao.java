package com.cc.dao;

import com.cc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 获取用户
     *
     * @param id 用户ID
     * @return User
     */
    User getUserById(Integer id);

    /**
     * 通过用户名获取用户
     *
     * @param name 用户名
     * @return User
     */
    User getUserByName(String name);
}
