package com.example.springstore.util;

import java.io.Serializable;

/*
*Json格式的数据进行响应
 */
public class JsonResult<E> implements Serializable {
    //响应的状态码
    private Integer state;
    //描述信息
    private String message;
    //对应的数据,不确定数据类型用泛型
    private E data;

    public JsonResult() {
    }


    public JsonResult(Integer state) {
        this.state = state;
    }
    //异常捕获
    public JsonResult(Throwable e) {
        this.message=e.getMessage();
    }
    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String message, E data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
