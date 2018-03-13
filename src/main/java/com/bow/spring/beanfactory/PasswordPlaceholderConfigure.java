package com.bow.spring.beanfactory;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author ViVi
 * @date 2015年9月12日 下午1:45:44
 */
public class PasswordPlaceholderConfigure extends PropertyPlaceholderConfigurer {

    @Override
    public String convertProperty(String propertyName, String propertyValue) {
        if ("password" == propertyName) {
            // 进行密码解码
            System.out.println("decrypt password");
        }
        return super.convertProperty(propertyName, propertyValue);
    }
}
