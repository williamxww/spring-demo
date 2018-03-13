package com.bow.spring.beanfactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 自定义注解{@link MyAutowired}
 * 
 * @author vv
 * @since 2017/2/8.
 */
@Configuration
public class InjectTest {

    @Component
    public static class BeanClass {
        @MyAutowired
        private FieldClass field;

        public void print() {
            field.print();
        }
    }

    @Component
    public static class FieldClass {
        public void print() {
            System.out.println("vv");
        }
    }

    @Bean
    public static MyAutowiredPostProcessor myAutowiredPostProcessor() {
        return new MyAutowiredPostProcessor();
    }

    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InjectTest.class);
        BeanClass beanClass = context.getBean(BeanClass.class);
        beanClass.print();
    }
}
