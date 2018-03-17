package com.bow.spring.property;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/** 
 * @author ViVi
 * @date 2015年8月26日 下午9:33:43  
 */

public class PersonPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(Person.class, new PersonEditor());
    }

}
