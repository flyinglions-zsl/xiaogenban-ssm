package com.ssm.filter;

import com.alibaba.fastjson.JSONObject;
import com.ssm.domain.SysUser;
import com.ssm.utils.JwtUtil;
import com.ssm.utils.R;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * @program: xiaogenban
 * @description: 基本拦截器
 * @author: FlyingLion
 * @create: 2019-08-16 22:26
 **/
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(LoginInterceptor.class);

    private static List<String> baseMethod = Arrays.asList("login","swagger-ui","ui","swagger-resources","api-docs");

    /**
     * 在请求抵达controller之前，拦截每次请求，登录、静态资源除外
     * token有效时间设置为15min，每次请求到达时进行有效期的判断，过期返回error，否则刷新过期时间，往后再推15min
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("访问地址：" + request.getRequestURL());
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Token");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String token = request.getHeader("access_token");
        String servletPath = request.getServletPath();
        String methodName = "";
        if (servletPath.contains("."))
            methodName = servletPath.substring(servletPath.lastIndexOf("/")+1,servletPath.lastIndexOf("."));
        else
            methodName = servletPath.substring(servletPath.lastIndexOf("/")+1,servletPath.length());
        System.out.println(methodName);
        R r = R.success();
        if (isInterceptedMethod(methodName)) {
            if (token != null) {
                //校验token有效性
                SysUser user = JwtUtil.unsign(token, SysUser.class);
                //有效，重新更新有效时间(暂定为生成时设定的有效时间)
                if (user != null){
                    //String new_token = JwtUtil.sign(user.getClass(),60L* 1000L* 15L);
                    return true;
                }else {
                    r = R.error("token失效，请重新登录");
                    responseMessage(response, response.getWriter(), r);
                    return false;
                }
            } else {
                r = R.error("token为空，请检查");
                responseMessage(response, response.getWriter(), r);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
    //返回错误信息
    private void responseMessage(HttpServletResponse response, PrintWriter writer, R r){
       response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(r);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    //验证是否需要拦截
    public boolean isInterceptedMethod(String methodName){
        boolean flag = true;
        if (baseMethod.contains(methodName))
            flag = false;
        return flag;
    }
}