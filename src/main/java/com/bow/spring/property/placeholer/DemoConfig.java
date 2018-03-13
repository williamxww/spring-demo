package com.bow.spring.property.placeholer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 测试自定义Configurer
 * 
 * @author vv
 * @since 2018/1/2.
 */
@Configuration
public class DemoConfig {

    @Component
    public static class Phone {
        @Value("${name}")
        private String name;

        public String getName() {
            return name;
        }
    }

    @Bean
    public DemoPropertyConfigurer config() {
        return new DemoPropertyConfigurer();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        Phone phone = context.getBean(Phone.class);
        System.out.println(phone.getName());
    }
}
