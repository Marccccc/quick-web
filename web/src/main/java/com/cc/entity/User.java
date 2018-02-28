package com.cc.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础用户类
 */
@Data
public class User implements UserDetails{

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

    List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(Role::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Date getLastPasswordResetDate(){
        return this.lastLoginTime;
    }
}
