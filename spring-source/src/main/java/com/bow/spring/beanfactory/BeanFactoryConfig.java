package com.bow.spring.beanfactory;

import java.beans.PropertyEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import com.bow.spring.property.Company;
import com.bow.spring.property.Person;
import com.bow.spring.property.PersonPropertyEditor;

/**
 * 通过此类描述bean factory初始化时的核心组件的顺序<br/>
 *
 * 1. {@link BeanFactoryPostProcessor#postProcessBeanFactory}<br/>
 * 2. {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation}<br/>
 * 3. 实体bean的setter<br/>
 * 4. {@link InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation}<br/>
 * 5. {@link InstantiationAwareBeanPostProcessor#postProcessPropertyValues}<br/>
 * 6. {@link BeanPostProcessor#postProcessBeforeInitialization}<br/>
 * 7. {@link InitializingBean#afterPropertiesSet()}<br/>
 * 8. {@link BeanPostProcessor#postProcessAfterInitialization}<br/>
 *
 * TODO PropertyEditor 在注解配置下，怎么搞？，下面PersonPropertyEditor没生效
 * @see PropertyEditor
 *
 * @author vv
 * @since 2017/4/30.
 */
@Configuration
@PropertySource(value = "classpath:com/bow/spring/beanfactory/bfpp.properties")
public class BeanFactoryConfig {

    /**
     * BeanFactoryPostProcessor
     */
    @Component
    public static class DemoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
        private static final Logger LOGGER = LoggerFactory.getLogger(DemoBeanFactoryPostProcessor.class);
        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            LOGGER.info("postProcessBeanFactory");
        }
    }

    /**
     * BeanPostProcessor
     *
     * @return
     */
    @Bean
    public DemoBeanPostProcessor demoBeanPostProcessor() {
        return new DemoBeanPostProcessor();
    }

    /**
     * InstantiationAwareBeanPostProcessor
     *
     * @return
     */
    @Bean
    public DemoInstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor() {
        return new DemoInstantiationAwareBeanPostProcessor();
    }

    /**
     * 注意这里用的是${@link PropertySourcesPlaceholderConfigurer}
     * ,并且是static的，需要在其他实例前完成初始化
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer password() {
        PropertySourcesPlaceholderConfigurer placeholderConfigure = new PropertySourcesPlaceholderConfigurer();
        return placeholderConfigure;
    }

    @Value("${simpleBean.password}")
    private String password;

    @Bean
    public SimpleBean simpleBean() {
        SimpleBean bean = new SimpleBean();
        bean.setPassword(password);
        return bean;
    }

    /**
     * 容器自动的找到PersonPropertyEditor，将vv转换为Person对象
     *
     * @see PersonPropertyEditor
     */
    @Value("vv")
    private Person person;

    @Bean
    public PersonPropertyEditor personPropertyEditor(){
        return new PersonPropertyEditor();
    }

    @Bean
    public Company company() {
        Company company = new Company();
        company.setEmployee(person);
        return company;
    }


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanFactoryConfig.class);
        SimpleBean simpleBean = context.getBean(SimpleBean.class);
        System.out.println(simpleBean.getPassword());
        Company company = context.getBean(Company.class);
        System.out.println(company.getEmployee().getAge());
    }


}
