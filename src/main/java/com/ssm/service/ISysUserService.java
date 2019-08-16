package com.ssm.service;

import com.ssm.domain.SysUser;
import com.ssm.utils.R;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ISysUserService {
    
    
    
    
    
    
    /**
     *@Description: 登录验证
     *@Param: [username, password, request]
     *@return: java.lang.String
     *@Author: FlyingLion
     *@Date: 2019/8/15 0015
     **/
    Map<String, Object> login(String username, String password, HttpServletRequest request);


    /**
     *@Description: 根据主键查询user
     *@Param: [uid]
     *@return: com.ssm.domain.SysUser
     *@Author: FlyingLion
     *@Date: 2019/8/16 0016
     **/
    SysUser selectByPrimaryKey(Integer uid);
}
