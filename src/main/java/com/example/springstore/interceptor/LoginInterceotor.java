package com.example.springstore.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.HandlerInterceptor;

/**定义一个拦截器*/
public class LoginInterceotor implements HandlerInterceptor {
    /**
     * 检测全局session对象是否有uid数据，如果有则放行，如果没有则重定向登录页面
     * @param request
     * @param response
     * @param handler 处理器（url+Controller：映射）
     * @return   返回值true放行  否则拦截
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //HttpServletRequest对象来获取session
        Object obj = request.getSession().getAttribute("uid");
        if(obj==null){  //没有登录系统重定向login.html
            response.sendRedirect("/web/login.html");
            //结束后面的调用
            return false;
        }
        //放行
        return true;
    }
}
