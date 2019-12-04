package com.ssm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.ssm.constant.PropertyConstant;
import com.ssm.utils.PropertyPlaceholderConfigurerExt;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @program: xiaogenban
 * @description: 配置类
 * @author: FlyingLion
 * @create: 2019-08-02 09:29
 **/
@Configuration
@MapperScan("com.ssm.mapper")
//@ImportResource({"classpath:spring-mvc.xml"})
//@PropertySource("classpath:jdbc.properties")
public class DruidDataSourceConfig {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.username}")
    private  String username;

    @Value("${jdbc.password}")
    private  String password;

    /**
     *@Description: 数据源配置
     *@Param: []
     *@return: javax.sql.DataSource
     *@Author: FlyingLion
     *@Date: 2019/8/7 0007
     **/
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        System.out.println("-----------" + password);
        return dataSource;
    }

    /**
     *@Description: mybatis整合spring配置
     *@Param: []
     *@return: org.mybatis.spring.SqlSessionFactoryBean
     *@Author: FlyingLion
     *@Date: 2019/8/7 0007
     **/
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception{
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        //配置mapper.xml
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String mapperLocations = PathMatchingResourcePatternResolver.CLASSPATH_URL_PREFIX+"mapper/*.xml";
        String configLocations = PathMatchingResourcePatternResolver.CLASSPATH_URL_PREFIX+"mybatis.xml";
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setTypeAliasesPackage("com.ssm.domain"); //设置别名，让*Mpper.xml实体类映射可以不加上具体包名
        sessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        sessionFactoryBean.setConfigLocation(resolver.getResource(configLocations));
        return sessionFactoryBean;
    }

    /**
     *@Description: 事务管理器
     *@Param: []
     *@return: org.springframework.jdbc.datasource.DataSourceTransactionManager
     *@Author: FlyingLion
     *@Date: 2019/8/7 0007
     **/
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource());
        return manager;
    }

    /**
     *@Description: 事务拦截器
     *@Param: []
     *@return: org.springframework.transaction.interceptor.TransactionInterceptor
     *@Author: FlyingLion
     *@Date: 2019/8/7 0007
     **/
    @Bean(name = "transactionInterceptor")
    public TransactionInterceptor transactionInterceptor(){
        TransactionInterceptor interceptor = new TransactionInterceptor();
        interceptor.setTransactionManager(dataSourceTransactionManager());

        Properties transactionProperties = new Properties();
        transactionProperties.setProperty("save*","PROPAGATION_REQUIRED");
        transactionProperties.setProperty("update*","PROPAGATION_REQUIRED");
        transactionProperties.setProperty("del*","PROPAGATION_REQUIRED");
        transactionProperties.setProperty("get*","PROPAGATION_REQUIRED,readOnly");
        transactionProperties.setProperty("find*","PROPAGATION_REQUIRED,readOnly");
        transactionProperties.setProperty("*","PROPAGATION_REQUIRED");

        interceptor.setTransactionAttributes(transactionProperties);
        return interceptor;
    }

    @Bean
    public PropertyPlaceholderConfigurerExt placeholderConfigurerExt() throws Exception {
        PropertyPlaceholderConfigurerExt placeholderConfigurerExt = new PropertyPlaceholderConfigurerExt();
        Properties properties = new Properties();
        //InputStream inputStream1 = new FileInputStream("classpath:jdbc.properties");
        InputStream inputStream = DruidDataSourceConfig.class.getResourceAsStream("/jdbc.properties");
        if (inputStream != null)
            properties.load(inputStream);
        placeholderConfigurerExt.setProperties(properties);
        //Resource resource = new ClassPathResource("classpath:jdbc.properties");
       // placeholderConfigurerExt.setLocation(resource);
        System.out.println(properties.getProperty(PropertyConstant.DES_KEY));
        System.out.println(properties.getProperty(PropertyConstant.JDBC_PASSWORD));
        return placeholderConfigurerExt;
    }
}