package com.example.springstore.service;

import com.example.springstore.entity.User;
import com.example.springstore.mapper.UserMapper;
import com.example.springstore.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;

@SpringBootTest  //表示是一个测试类，不会随项目打包发送
@RunWith(SpringRunner.class)  //@RunWith：表示启动这个单元测试类，不写单元测试类是不能运行的，需要传递一个参数，参数必须是SpringRunner的实例类型
public class UserServiceTest {
    /*
    1.必须@Test注解
    2.返回值必须void
    3.方法的参数列表不指定类型
    4.方法访问修饰符必须public
     */
    @Autowired
    private IUserService iUserService;
    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("路");
            user.setPassword("123456");
            iUserService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            //获取具体信息
            System.out.println(e.getMessage());
            //获取类的对象，获取类的名称
            System.out.println(e.getClass().getSimpleName());
        }
    }
    @Test
    public void login() {
        try {
            String username = "tom";
            String password = "123456";
            User user = iUserService.login(username, password);
            System.out.println("登录成功！" + user);
        } catch (ServiceException e) {
            System.out.println("登录失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changePassWorod(){
        iUserService.changePassword(9,"管理员","123456","1234567");
    }

}
