package com.ssm.filter;

import com.alibaba.fastjson.JSONObject;
import com.ssm.domain.SysUser;
import com.ssm.utils.JwtUtil;
import com.ssm.utils.R;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @program: xiaogenban
 * @description: 基本拦截器
 * @author: FlyingLion
 * @create: 2019-08-16 22:26
 **/
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 拦截每次请求，登录、静态资源除外
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        R r = R.success();
        if (token != null){
            SysUser user = JwtUtil.unsign(token, SysUser.class);
            String userId = request.getParameter("userId");
            if (user != null && userId != null){
                if (userId == user.getUid().toString()){
                    return true;
                }else {
                    r = R.error("token失效，请检查");
                    responseMessage(response,response.getWriter(),r);
                    return false;
                }
            }else {
                r = R.error("user或userId为空，请检查");
                responseMessage(response,response.getWriter(),r);
                return false;
            }
        }else {
            r = R.error("token为空，请检查");
            responseMessage(response,response.getWriter(),r);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
    //返回错误信息
    private void responseMessage(HttpServletResponse response, PrintWriter writer, R r){
       /* response.setContentType("application/json; chaset=utf-8");
        String json = JSONObject.toJSONString(r);
        writer.write(json);
        writer.flush();
        writer.close();*/
    }
}