package com.bow.spring.rmi.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author acer
 * @since 2016年2月28日
 */
public class Server {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:com/bow/spring/rmi/server/applicationContext-server.xml");
        System.out.println("server started...");
    }
}
