package com.example.springstore.controller;

import com.example.springstore.service.ex.*;
import com.example.springstore.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.ServerException;

/*
控制层类的基类
 */
public class BaseController {
    /** 操作成功的状态码 */
    public static final int OK = 200;
    /** &#064;ExceptionHandler用于统一处理方法抛出的异常  */
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名被占用异常");
        } else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("用户名不存在异常");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("用户名的密码错误异常");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("插入数据时产生的未知异常");
        }else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("更新数据时产生的未知异常");
        }
        return result;
    }
    /**
     * 获取session的uid
     * sessin的对象
     * 当前登录的uid
     * */

    protected final Integer getuidFromSession(HttpSession session){

        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    /**获取username*/
    protected final String getUsernameFromSession(HttpSession session){

        return session.getAttribute("username").toString();
    }

}
