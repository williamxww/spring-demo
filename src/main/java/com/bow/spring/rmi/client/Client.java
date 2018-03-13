package com.bow.spring.rmi.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bow.spring.rmi.Calculate;

/**
 * @author acer
 * @version C10 2016年2月28日
 * @since 2016年2月28日
 */
public class Client
{
    @SuppressWarnings("resource")
    public static void main(String[] args)
    {
        ApplicationContext context =
            new ClassPathXmlApplicationContext("classpath:spring/rmi/client/applicationContext-client.xml");
        Calculate c = context.getBean(Calculate.class);
        System.out.println(c.addOne(2));
    }
}
