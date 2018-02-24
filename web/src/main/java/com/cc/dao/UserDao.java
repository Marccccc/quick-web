package com.cc.dao;

import com.cc.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 获取用户
     *
     * @param id 用户ID
     * @return User
     */
    User getUserById(Integer id);

}