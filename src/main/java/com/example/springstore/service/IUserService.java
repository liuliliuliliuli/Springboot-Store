package com.example.springstore.service;

import com.example.springstore.entity.User;
import org.springframework.stereotype.Service;

/*
用户模块业务层接口
 */
public interface IUserService {
    /**
    *用户注册方法  * user用户的数据对象*/
    void reg(User user);  //注册register
    /** 用户登录，username和password匹配当前数据，如果没有则返回null*/
    User login (String username, String password);  //登录
    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

}
