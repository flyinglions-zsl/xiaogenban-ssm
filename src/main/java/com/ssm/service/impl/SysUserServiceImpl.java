package com.ssm.service.impl;

import com.ssm.domain.SysUser;
import com.ssm.mapper.SysUserMapper;
import com.ssm.service.ISysUserService;
import com.ssm.utils.JwtUtil;
import com.ssm.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: xiaogenban
 * @description: 用户业务类实现类
 * @author: FlyingLion
 * @create: 2019-08-15 23:48
 **/
@Service
public class  SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 添加jwt验证
     * */
    @Override
    public Map<String, Object> login(String username, String password, HttpServletRequest request) {
        SysUser user = new SysUser();
        Map<String, Object> maps = new HashMap<>();
        user.setUname(username);
        user.setPassword(password);
        //先数据库验证--得到userId
        Integer userId = userMapper.checkLogin(username,password);
        if(userId > 0){
            SysUser sysUser = userMapper.selectByPrimaryKey(userId);
            user.setUid(userId);
            //使用jwt加密给用户生成token--15分钟有效
            String token = JwtUtil.sign(user,60L* 1000L* 15L);
            maps.put("userId", userId);
            maps.put("token", token);
            maps.put("user", sysUser);
        }
        return maps;
    }

    @Override
    public SysUser selectByPrimaryKey(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }



}