package com.bow.spring.beanfactory;

import java.beans.PropertyDescriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * 测试InstantiationAwareBeanPostProcessor 在bean的实例化和初始化前后放置扩展点<br/>
 * 触发顺序参照{@link BeanFactoryConfig}
 *
 * @author vv
 * @since 2017/2/1.
 */
public class DemoInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoInstantiationAwareBeanPostProcessor.class);

    /**
     * 若此方法返回的结果不为null，则会中断后面bean对象的创建过程
     * 
     * @param beanClass beanClass
     * @param beanName beanName
     * @return Object
     * @throws BeansException e
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        LOGGER.info("postProcessBeforeInstantiation");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        LOGGER.info("postProcessAfterInstantiation");
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean,
            String beanName) throws BeansException {
        LOGGER.info("postProcessPropertyValues");
        return pvs;
    }

    /**
     * 在下列情况前处理 {@link InitializingBean}
     * ,或是bean自身配置了init-method
     * 
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("postProcessAfterInitialization");
        return bean;
    }
}
