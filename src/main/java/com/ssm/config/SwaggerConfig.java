package com.ssm.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: xiaogenban
 * @description: web配置类
 * @author: FlyingLion
 * @create: 2019-08-02 09:48
 **/
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.groupName("pc")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //.apis(RequestHandlerSelectors.basePackage("com.ssm.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("xgbAPI")//小跟班管理系统
                .description("interfaces")//接口信息
                .termsOfServiceUrl("http://localhost/xiaogenban-ssm/")
                .contact(new Contact("xgb","http://localhost/xiaogenban-ssm/",""))
                .version("1.0")
                .build();
    }
}