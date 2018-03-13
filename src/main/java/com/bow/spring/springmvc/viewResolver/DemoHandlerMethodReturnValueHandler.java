package com.bow.spring.springmvc.viewResolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bow.spring.springmvc.ViewName;

/**
 * @author vv
 * @since 2017/2/2.
 */
public class DemoHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    /**
     * 哪类类型的返回值将启用该返回值处理器
     * 
     * @param returnType
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getParameterType().isAssignableFrom(ViewName.class);
    }

    /**
     * 返回值的处理逻辑，并且将处理好的值返回给model
     * 
     * @param returnValue
     * @param returnType
     * @param mavContainer
     * @param webRequest
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws Exception {
        System.out.println("handleReturnValue");
        ViewName viewName = (ViewName) returnValue;
        mavContainer.setViewName(viewName.getName());
    }
}
