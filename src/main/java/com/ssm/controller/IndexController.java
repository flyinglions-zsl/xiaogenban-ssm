package com.ssm.controller;

import com.ssm.service.ISysUserService;
import com.ssm.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: xiaogenban
 * @description: 主页模块
 * @author: FlyingLion
 * @create: 2019-08-15 21:32
 **/
@Api(value = "主页模块")
@RestController
public class IndexController {

    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation(value = "登录验证",httpMethod = "POST")
    @RequestMapping(value = "/login")
    public R login(String username, String password, HttpServletRequest request){
        int code = sysUserService.login(username,password,request);
        if(code != 1){
            return R.error("账号或密码错误");
        }
        return R.success();
    }
}