package com.bow.spring.beanfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 在bean初始化(Initialization)的前后放置扩展点<br/>
 * 如果bean实现了{@link InitializingBean} 在是在
 * {@link InitializingBean#afterPropertiesSet()}方法前后触发
 * 
 * @author vv
 * @since 2017/2/1.
 */
public class DemoBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoBeanPostProcessor.class);
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
