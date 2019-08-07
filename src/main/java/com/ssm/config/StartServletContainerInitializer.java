package com.ssm.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: xiaogenban-ssm
 * @description:
 * @author: FlyingLion
 * @create: 2019-08-07 15:28
 **/
@HandlesTypes(WebApplicationInitializer.class)
public class StartServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> classSet, ServletContext servletContext) throws ServletException {
        if (classSet != null && !classSet.isEmpty()) {
            classSet.forEach(e -> {
                //不是接口，也不是抽象类
                if (!e.isInterface() && !Modifier.isAbstract(e.getModifiers()) &&
                        WebApplicationInitializer.class.isAssignableFrom(e)) {
                    try {
                        WebApplicationInitializer webApplication = (WebApplicationInitializer) e.newInstance();
                        webApplication.onStartup(servletContext);
                    } catch (InstantiationException e1) {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    } catch (ServletException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        //添加Servlet
        /*ServletRegistration.Dynamic dynamicServlet = servletContext.addServlet("demoServlet", new DemoStartServlet());
        //请求路径
        dynamicServlet.addMapping("/demo");
        //Servlet InitParam
        dynamicServlet.setInitParameter("demo", "demo");
        dynamicServlet.setLoadOnStartup(1);
        //添加过滤器
        FilterRegistration.Dynamic dynamicFilter = servletContext.addFilter("filter", new DemoFilter());
        dynamicFilter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "demoServlet");*/

    }
}