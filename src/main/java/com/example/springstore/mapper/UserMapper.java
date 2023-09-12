package com.example.springstore.mapper;

import com.example.springstore.entity.User;

import java.util.Date;

/*
用户模块的持久层接口
 */
public interface UserMapper {
    /*
    *插入用户数据
    * @param user 用户 的数据
    * @return 返回值是否执行
     */
    Integer insert(User user);
    /*
    *根据用户名来查询用户的数据
    * username 用户名
    * 如果找到，返回Null
     */
    User findByUsername(String username);


    /**
     * 根据用户的uid修改密码
     * @param uid 用户id
     * @param password  输入的新密码
     * @param modifiedUser  表示修改的执行者
     * @param modifiedTime  表示修改的时间
     * @return 返回受影响的行数
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的id查询用户的数据
     * @param uid
     * @return 如果找到返回对象，否则null
     */
    User findByUid(Integer uid);
}
