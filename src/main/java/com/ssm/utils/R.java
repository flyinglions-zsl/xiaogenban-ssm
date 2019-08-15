package com.ssm.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @program: xiaogenban
 * @description: result返回类
 * @author: FlyingLion
 * @create: 2019-08-15 21:42
 **/
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class R implements Serializable {

    private static final long serialVersionUID = 1L;

    private static String SUCCESS_MSG = "请求处理成功";
    private static String ERROR_MSG = "请求处理失败";
    private static String DATA_NULL = "请求数据为空";
    private static String PARAMETER_LACK = "参数缺失";
    private static String INTERNAL_MSG = "未知异常，请通知管理员";

    private static Integer SUCCESS_CODE = 200; //请求成功

    private static Integer SUCCESS_STATUS = 1; //成功
    private static Integer ERROR_STATUS = -1;  //失败
    private static Integer DATA_NULL_STATUS = 0; //数据为空

    //状态码
    private Integer status;
    //响应码
    private Integer code;
    //响应消息
    private String msg;
    //响应数据
    private Object data;

    public static R success(){
        R r = new R();
        r.setMsg(R.SUCCESS_MSG);
        r.setCode(R.SUCCESS_CODE);
        r.setStatus(R.SUCCESS_STATUS);
        return r;
    }

    public static R success(Object data){
        R r = new R();
        r.setMsg(R.SUCCESS_MSG);
        r.setCode(R.SUCCESS_CODE);
        r.setStatus(R.SUCCESS_STATUS);
        r.setData(data);
        return r;
    }

    public static R error(){
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(),INTERNAL_MSG);
    }

    //可自定义报错信息
    public static R error(String msg){
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(),msg);
    }

    public static R error(Integer code, String msg){
        R r = new R();
        r.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setMsg(msg);
        r.setStatus(R.ERROR_STATUS);
        return r;
    }
}