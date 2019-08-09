package com.ssm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: xiaogenban-ssm
 * @description: 总的配置类
 * @author: FlyingLion
 * @create: 2019-08-07 10:28
 **/
/*@Configuration
@ComponentScan(basePackages = {"com.ssm"},useDefaultFilters = true
        ,excludeFilters  = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)})
@Import(DruidDataSourceConfig.class)*/
/*@EnableTransactionManagement*/
public class AppConfig {

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
   /* @Bean
    public BeanNameAutoProxyCreator proxyCreator(){
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setProxyTargetClass(true);
        creator.setBeanNames("*ServiceImpl");
        creator.setInterceptorNames("transactionInterceptor");
        return creator;
    }*/
}