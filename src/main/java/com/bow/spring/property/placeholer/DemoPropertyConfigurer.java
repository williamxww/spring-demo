package com.bow.spring.property.placeholer;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * @see #order 前一个configurer没找到，后面configurer继续找，多个configurer时，
 *      ignoreUnresolvablePlaceholders一定要设置为true
 * @see #processProperties 用props开始填充BeanFactory中的占位符
 * @author vv
 * @since 2018/1/2.
 */
@Component
public class DemoPropertyConfigurer extends PropertyPlaceholderConfigurer {

    public DemoPropertyConfigurer() {

    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props) {
        // 此处添加从configClient取值逻辑，这样就可以废弃spring从配置文件中取配置项的逻辑
        if ("name".equals(placeholder)) {
            return "vv";
        }
        return super.resolvePlaceholder(placeholder, props);
    }

    /**
     * 将程序指定的props合并到spring的配置仓库中
     * 
     * @see #mergeProperties() 我们也可以重写此方法给spring返回一个总的properites文件
     * @param props 代码动态指定的props
     */
    public void setProperties(Properties props) {
        super.setProperties(props);
    }
}
