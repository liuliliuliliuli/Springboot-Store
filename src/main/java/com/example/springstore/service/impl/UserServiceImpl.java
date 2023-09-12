package com.example.springstore.service.impl;

import com.example.springstore.entity.User;
import com.example.springstore.mapper.UserMapper;
import com.example.springstore.service.IUserService;
import com.example.springstore.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

/*
用户模块业务层的实现类
 */
@Service   //将当前类的对象交给Spring来管理，能自动创建对象以及对象的维护
public class UserServiceImpl  implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        //通过user传过来的参数获取usernam
        String username = user.getUsername();

        //调用findByUsername("")判断用户是否注册过
        User result = userMapper.findByUsername(username);
        //判断是否为NULL，如果不是则抛出用户名被占用的异常
        if(result != null){
            //抛出异常
            throw  new UsernameDuplicatedException("用户被占用");
        }

        //密码加密处理 ：md5算法形式，68dhsdasdsadasdasdsa
        //串+password+串 ----md5加密，连续加载3次
        //盐值+password+盐值---盐值是个随机的字符串
        String oldpassword = user.getPassword();
        //获取盐值（随机生产）
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密处理
        String md5Password = getMD5Password(oldpassword,salt);
        user.setPassword(md5Password);

        //补全的数据：is_delete设置为0
        user.setIsDelete(0);
        //：4个日志信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册功能,row==1插入成功
        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw new InsertException("用户在注册过程中产生未知的异常");
        }
    }

    /*
    定义个一个md5算法的加密处理
     */
    private String getMD5Password(String password, String salt){
        for (int i=0;i<3;i++){
            //md5加密算法方法的调用,3次加密
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }

        return password;

    }


    @Override
    public User login(String username, String password) {
        //根据用户名称查询是否存在，如果不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if(result == null){
            throw new UserNotFoundException("用户不存在");
        }
        //判断is_delete是否为1注销   0存在；
        if(result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据已经注销");
        }

        //检测用户的密码是否匹配
        //1.先获取数据库中加密之后的密码进行比较
        String oldPassword = result.getPassword();
        //2.将用户的密码按照相同的md5的算法进行加密，所以需要先获取盐值
        String salt = result.getSalt();
        String md5Password = getMD5Password(password, salt);
        //3.将密码比较
        if(!oldPassword.equals(md5Password)){
            throw new PasswordNotMatchException("密码错误");
        }


        // 将查询结果中的uid、username、avatar封装到新的user对象中
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        //返回用户数据，返回的数据是为了辅助其他页面做数据展示使用
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");

        }
        //原始密码和数据库密码比较
        String oldMD5Password=getMD5Password(oldPassword, result.getSalt());
        if(!result.getPassword().equals(oldMD5Password)){
            throw new PasswordNotMatchException("密码错误");
        }
        //将新密码设置到数据库，将新的密码进行加密再去更新
        String newMD5Password = getMD5Password(newPassword, result.getSalt());
        Integer row = userMapper.updatePasswordByUid(uid,newMD5Password,username,new Date());
        if(row != 1){
            throw new UpdateException("更新数据产生未知异常");
        }

    }
}

