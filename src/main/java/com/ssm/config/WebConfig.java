package com.ssm.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ssm.filter.LoginInterceptor;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;


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

    /**
     *@Description: 会注册一个默认的Handler：DefaultServletHttpRequestHandler
     * ，这个Handler也是用来处理静态文件的,映射/
     * 静态资源是放置在web根目录下，而非WEB-INF 下
     * 可以理解为DispatcherServlet破坏了Servlet的一个特性（根目录下的文件可以直接访问）
     * ，DefaultServletHttpRequestHandler是帮助回归这个特性的
     *@Param: [configurer]
     *@return: void
     *@Author: FlyingLion
     *@Date: 2019/8/16 0016
     **/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     *@Description: 配置拦截器
     *@Param: [registry]
     *@return: void
     *@Author: FlyingLion
     *@Date: 2019/8/16 0016
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }

   /* @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //调用父类的配置
        super.configureMessageConverters(converters);
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
    }*/
}