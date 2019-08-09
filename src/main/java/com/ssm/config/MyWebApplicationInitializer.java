package com.ssm.config;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @program: xiaogenban-ssm
 * @description:
 * @author: FlyingLion
 * @create: 2019-08-07 12:24
 **/
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(WebConfig.class);
       /* rootContext.refresh();*/
        //添加logback的监听器
        container.addListener(new LogbackConfigListener());
        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(rootContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}