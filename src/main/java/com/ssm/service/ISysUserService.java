package com.ssm.service;

import javax.servlet.http.HttpServletRequest;

public interface ISysUserService {
    
    
    
    
    
    
    /**
     *@Description: 登录验证
     *@Param: [username, password, request]
     *@return: java.lang.String
     *@Author: FlyingLion
     *@Date: 2019/8/15 0015
     **/
    int login(String username, String password, HttpServletRequest request);
    
}
