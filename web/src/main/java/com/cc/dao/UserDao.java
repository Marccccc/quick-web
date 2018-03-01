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
     * 通过用户账户获取用户
     *
     * @param account 用户账户
     * @return User
     */
    User getUserByAccount(String account);

    /**
     * 保存用户
     *
     * @param user 新增用户用户
     * @return User
     */
    int save(User user);

}
