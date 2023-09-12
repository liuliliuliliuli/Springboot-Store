package com.example.springstore.controller;

import com.example.springstore.entity.User;
import com.example.springstore.service.IUserService;
import com.example.springstore.service.ex.InsertException;
import com.example.springstore.service.ex.ServiceException;
import com.example.springstore.service.ex.UsernameDuplicatedException;
import com.example.springstore.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*

 */
@RestController    //相当于@Controller +@ResponseBody 就不用写下面那个
@RequestMapping("users")
public class UserController extends BaseController{
    /**
     * 1.接收数据方法：请求处理方法的参数列表设置为非pojo类型。、。
     * SPringBOot会直接将请求的参数名和方法参数名直接进行比较，如果相同则直接完成依赖注入
     */
    @Autowired
    private IUserService iUserService;
    @RequestMapping("reg")
    //@ResponseBody  //表示此方法的响应结果以json格式的数据进行响应给到前端

    public JsonResult<Void> reg(User user){
        iUserService.reg(user);
        return new JsonResult<Void>(OK); /* @RequestMapping("reg")
    //@ResponseBody  //表示此方法的响应结果以json格式的数据进行响应给到前端*/

    }
    @RequestMapping("login")
    public  JsonResult<User> login(String username, String password, HttpSession session){
        User data = iUserService.login(username, password);
        //像session对象完成绑定（session全局）
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        iUserService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<Void>(OK);
    }


}
