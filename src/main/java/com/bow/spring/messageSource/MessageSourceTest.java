package com.bow.spring.messageSource;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring国际化实现 和基础java 国际化实现
 * 
 * @author ViVi
 * @date 2015年8月22日 下午7:37:07
 */

public class MessageSourceTest {

    public void testLanguage() {
        @SuppressWarnings("resource")
        MessageSource resources = new ClassPathXmlApplicationContext(
                "com/bow/spring/messageSource/applicationContext-messages.xml");
        String[] param = { "名字" };
        String message = resources.getMessage("param.null", param, "Default", Locale.CHINA);
        System.out.println(message);
        String messageEn = resources.getMessage("param.null", param, "Default", Locale.US);
        System.out.println(messageEn);
    }

    public void testResourceBundle() {
        Locale localeCh = new Locale("zh", "CN");
        ResourceBundle resbCh = ResourceBundle.getBundle("com/bow/spring/messageSource/messages", localeCh);
        System.out.println(resbCh.getString("param.null"));

        ResourceBundle resbDefault = ResourceBundle.getBundle("com/bow/spring/messageSource/messages", Locale.getDefault());
        System.out.println(resbDefault.getString("param.null"));

        Locale localeEn = new Locale("en", "US");
        ResourceBundle resbEn = ResourceBundle.getBundle("com/bow/spring/messageSource/messages", localeEn);
        System.out.println(resbEn.getString("param.null"));
    }
}
