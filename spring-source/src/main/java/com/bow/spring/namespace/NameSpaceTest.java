package com.bow.spring.namespace;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 命名空间中自定义标签
 *
 * @author acer
 * @since 2016年2月21日
 */
public class NameSpaceTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/namespace/application.xml");
        ApplicationConfig app = context.getBean("app1", ApplicationConfig.class);
        System.out.println(app.getName());
    }
}
