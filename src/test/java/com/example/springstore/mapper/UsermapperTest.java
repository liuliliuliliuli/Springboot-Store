package com.example.springstore.mapper;

import com.example.springstore.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest  //表示是一个测试类，不会随项目打包发送
@RunWith(SpringRunner.class)  //@RunWith：表示启动这个单元测试类，不写单元测试类是不能运行的，需要传递一个参数，参数必须是SpringRunner的实例类型
public class UsermapperTest {
    /*
    1.必须@Test注解
    2.返回值必须void
    3.方法的参数列表不指定类型
    4.方法访问修饰符必须public
     */
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("user01");
        user.setPassword("123456");
        Integer rows = userMapper.insert(user);
        System.out.println("rows=" + rows);
    }
    @Test
    public void findByUsername() {

        User result = userMapper.findByUsername("user01");
        System.out.println(result);
    }

    @Test
    public void  updatePasswordByUid(){
        userMapper.updatePasswordByUid(8,"1234567","管理员",new Date());
    }

    @Test
    public void  findByUid(){
        System.out.println(userMapper.findByUid(8));
    }

}
