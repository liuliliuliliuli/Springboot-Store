package com.example.springstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class SpringStoreApplicationTests {
    @Autowired //自动装配
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

        /*
        HikariProxyConnection@540537131 wrapping com.mysql.cj.jdbc.ConnectionImpl@3b021664
        数据库连接池：
        1.DBCP
        2.C3P0
        3.Hikari  :数据库连接对象
         */
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }


}
