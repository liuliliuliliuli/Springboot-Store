package com.example.springstore.config;

import com.example.springstore.interceptor.LoginInterceotor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/*处理器拦截器的注册**/
@Configuration  //加载当前拦截器进行注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    /*配置拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**自定义一个拦截器对象*/
        HandlerInterceptor interceptor = new LoginInterceotor();
        /**白名单*/
        List<String> patterns = new ArrayList<String>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");

        //完成拦截器的注册                                      //全部拦截  //除了
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns); //表示要拦截什么
    }
}
