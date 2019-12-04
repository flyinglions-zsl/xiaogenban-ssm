package com.ssm.utils;

import com.ssm.constant.PropertyConstant;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

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
