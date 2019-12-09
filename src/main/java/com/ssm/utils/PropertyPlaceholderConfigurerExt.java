package com.ssm.utils;

import com.ssm.constant.PropertyConstant;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;


/**
 * 自定义propertySource，实现数据源初始化之前修改peoperties文件信息
 * */
public class PropertyPlaceholderConfigurerExt extends PropertyPlaceholderConfigurer {

    private Properties properties;
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties properties) throws BeansException {
        //System.out.println("get in ----------------------------------");
        String jdbc_passwd = properties.getProperty(PropertyConstant.JDBC_PASSWORD);
        String des_key = properties.getProperty(PropertyConstant.DES_KEY);
        if (jdbc_passwd != null) {
            try {
                properties.setProperty(PropertyConstant.JDBC_PASSWORD,DesUtil.decrypt(DesUtil.jdkBase64Decoder(jdbc_passwd),des_key));
                this.properties = properties;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.processProperties(beanFactoryToProcess, properties);
    }

    // 可根据启动参数，动态读取配置文件 https://blog.csdn.net/china_shrimp/article/details/65938684
//    @Override
//    protected void loadProperties(Properties props) throws IOException {
//        props = this.properties;
//        super.loadProperties(props);
//    }



    /*@Override
    protected void convertProperties(Properties properties) {
        System.out.println("get in ----------------------------------");
        String jdbc_passwd = properties.getProperty(PropertyConstant.JDBC_PASSWORD);
        String des_key = properties.getProperty(PropertyConstant.DES_KEY);
        if (jdbc_passwd != null) {
            try {
                properties.setProperty(PropertyConstant.JDBC_PASSWORD,DesUtil.decrypt(DesUtil.jdkBase64Decoder(jdbc_passwd),des_key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.convertProperties(properties);
    }*/

}
