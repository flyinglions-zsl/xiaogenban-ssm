package com.ssm.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ssm.filter.LoginInterceptor;
import com.ssm.utils.PropertyPlaceholderConfigurerExt;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * @program: xiaogenban-ssm
 * @description: Web配置类
 * @author: FlyingLion
 * @create: 2019-08-07 11:26
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ssm")/*,useDefaultFilters = false
,includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)})*/
//@ImportResource({"classpath:spring-mvc.xml"})
@Import({DruidDataSourceConfig.class,SwaggerConfig.class})
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

   @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
                //List字段如果为null,输出为[],而非null
                //SerializerFeature.WriteNullListAsEmpty,
                //Boolean字段如果为null,输出为falseJ,而非null
                //SerializerFeature.WriteNullBooleanAsFalse,
                //消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.DisableCircularReferenceDetect,
                //是否输出值为null的字段,默认为false
                SerializerFeature.WriteMapNullValue,
                //字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullStringAsEmpty
        );
        //处理数据库返回时间戳问题，统一设置展示格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
       //调用父类的配置
       super.configureMessageConverters(converters);
    }

    /**
     *@Description: 静态资源
     * <mvc:resources mapping="/static/**" location="/static/" />
     * <mvc:resources mapping="swagger-ui.html" location="classpath:/META-INF/resources/" />
     * <mvc:resources mapping="/webjars/**" location="classpath:/META-INF/resources/webjars/" />
     *@Param: [registry]
     *@return: void
     *@Author: FlyingLion
     *@Date: 2019/8/21 0021
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}