package com.cc.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 基础用户类
 */
@Data
public class User {

    private int id;
    @Length(min = 3,max = 12,message = "登录账户为4-12位")
    private String account;
    @Length(min = 2,max = 12,message = "用户名为4-12位")
    private String name;
    @Length(min = 4,max = 12,message = "密码为4-12位")
    private String password;
    private boolean status;
    private String telephone;

    private Date lastLoginTime;

}
