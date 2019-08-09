package com.ssm.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @program: xiaogenban-ssm
 * @description: Web配置类
 * @author: FlyingLion
 * @create: 2019-08-07 11:26
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ssm",useDefaultFilters = false
        ,includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)})
@ImportResource({"classpath:spring-mvc.xml"})
@Import(DruidDataSourceConfig.class)
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     *@Description:
     * 1.@EnableTransactionManagement 注解，再加上配置 DataSourceTransactionManager 的bean
     * ，就可以在service实现层使用 @Transactional 注解为方法手动加上事务，并且指定的传播属性等等
     * 2.使用 BeanNameAutoProxyCreator 拦截代理方式，
     * 先创建一个 TransactionInterceptor bean，配置好事务传播等属性，在由 BeanNameAutoProxyCreator 进行事务代理
     *@Param: []
     *@return: org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator
     *@Author: FlyingLion
     *@Date: 2019/8/7 0007
     **/
    @Bean
    public BeanNameAutoProxyCreator proxyCreator(){
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setProxyTargetClass(true);
        creator.setBeanNames("*ServiceImpl");
        creator.setInterceptorNames("transactionInterceptor");
        return creator;
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}