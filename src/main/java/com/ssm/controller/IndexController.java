package com.ssm.controller;

import com.ssm.domain.SysUser;
import com.ssm.service.ISysUserService;
import com.ssm.utils.JwtUtil;
import com.ssm.utils.R;
import com.ssm.utils.VerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

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
    @RequestMapping(value = "/login.do", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public R login(
            @ApiParam(name = "username", value = "用户名", required = true) @RequestParam(value = "username", required = true) String username
            , @ApiParam(name = "password", value = "用户密码", required = true) @RequestParam(value = "password", required = true) String password
            , HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        if (username != null && password != null && request != null)
            map = sysUserService.login(username,password,request);
        if (map == null){
            return R.defaultMessage();
        }
        return R.success().put("TokenMap",map);
    }

    @ApiOperation(value = "获取验证码图片", httpMethod = "GET")
    @GetMapping(value = "/getVerifyCode.do")
    public void getVerificationCode(HttpServletRequest request, HttpServletResponse response){
        try {
        int width = 200;
        int height = 69;
        BufferedImage verifyImg=new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        //生成对应宽高的初始图片
        String randomText = VerifyCodeUtil.drawRandomText(width,height,verifyImg);
        //功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符
        request.getSession().setAttribute("verifyCode", randomText);
        response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
        OutputStream os = null; //获取文件输出流
        os = response.getOutputStream();
        ImageIO.write(verifyImg,"png",os);//输出图片流
        os.flush();
        os.close();//关闭流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}