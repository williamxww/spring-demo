package com.bow.spring.beanfactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author vv
 * @since 2017/2/8.
 */
@Component
public class MyAutowiredPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //发现MyAutowired注解，就注入一个同类型的bean
        Class beanClass = bean.getClass();
        do {
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields) {
                if (!hasAnnotation(field.getAnnotations(), MyAutowired.class.getName())) {
                    continue;
                }
                setField(bean, field);
            }
        } while ((beanClass = beanClass.getSuperclass()) != null);
        return bean;
    }

    private boolean hasAnnotation(Annotation[] annotations, String annotationName) {
        if (annotations == null) {
            return false;
        }
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().getName().equals(annotationName)) {
                return true;
            }
        }
        return false;
    }

    private void setField(Object bean, Field field) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            field.set(bean, applicationContext.getBean(field.getType()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
