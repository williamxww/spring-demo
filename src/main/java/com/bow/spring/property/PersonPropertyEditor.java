package com.bow.spring.property;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名为 Person + PropertyEditor, 这样就不需要显示的去注册 Person 属性对应的编辑器PersonPropertyEditor
 *
 * 符合规范且在同一个包中，容器能自动的找到Person对应的编辑器
 * 
 * @see com.bow.spring.beanfactory.BeanFactoryConfig
 * @author vv
 * @since 2017/4/30.
 */
public class PersonPropertyEditor extends PropertyEditorSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonPropertyEditor.class);

    @Override
    public void setAsText(String text) {
        LOGGER.info(text);
        Person p = new Person();
        p.setAge(27);
        p.setName(text);
        setValue(p);
    }
}
