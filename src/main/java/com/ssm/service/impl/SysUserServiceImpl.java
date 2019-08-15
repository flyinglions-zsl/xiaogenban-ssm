package com.ssm.service.impl;

import com.ssm.mapper.SysUserMapper;
import com.ssm.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: xiaogenban
 * @description: 用户业务类实现类
 * @author: FlyingLion
 * @create: 2019-08-15 23:48
 **/
@Service
public class SysUserServiceImpl implements ISysUserService {

    private SysUserMapper userMapper;

    /**
     * 添加jwt验证
     * */
    @Override
    public int login(String username, String password, HttpServletRequest request) {
        return userMapper.checkLogin(username,password);
    }
}