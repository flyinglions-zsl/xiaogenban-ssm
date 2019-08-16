package com.ssm.controller;

import com.ssm.domain.SysUser;
import com.ssm.service.ISysUserService;
import com.ssm.utils.JwtUtil;
import com.ssm.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
    public R login(
            @ApiParam(name = "username", value = "用户名", required = true) @RequestParam(value = "username", required = true) String username
            ,@ApiParam(name = "password", value = "用户密码", required = true) @RequestParam(value = "password", required = true) String password
            , HttpServletRequest request){
        return R.success().put("TokenMap",sysUserService.login(username,password,request));
    }
}