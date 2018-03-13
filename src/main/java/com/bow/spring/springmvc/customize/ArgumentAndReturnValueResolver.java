package com.bow.spring.springmvc.customize;

import java.util.List;

import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

/**
 * ArgumentAndReturnValueResolver,此类实现了{@link HandlerMethodArgumentResolver},
 * {@link HandlerMethodReturnValueHandler}<br/>
 *
 * 父类{@link AbstractMessageConverterMethodProcessor}通过
 * {@link HttpMessageConverter}写响应到流中<br/>
 * 父类{@link AbstractMessageConverterMethodArgumentResolver}通过
 * {@link HttpMessageConverter}从流中读请求<br/>
 *
 * @author vv
 * @since 2017/2/3.
 */
public class ArgumentAndReturnValueResolver extends AbstractMessageConverterMethodProcessor {

    public ArgumentAndReturnValueResolver(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    /**
     * @see HandlerMethodArgumentResolver
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MyRequestBody.class);
    }

    /**
     * @see HandlerMethodArgumentResolver
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object arg = readWithMessageConverters(webRequest, parameter, parameter.getGenericParameterType());
        String name = Conventions.getVariableNameForParameter(parameter);
        WebDataBinder binder = binderFactory.createBinder(webRequest, arg, name);
        if (arg != null) {
            validateIfApplicable(binder, parameter);
            if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
                throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
            }
        }
        mavContainer.addAttribute(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());
        return arg;
    }

    /**
     * @see HandlerMethodReturnValueHandler
     * @param returnType
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getMethodAnnotation(MyResponseBody.class) != null;
    }

    /**
     * @see HandlerMethodReturnValueHandler
     * @param returnValue
     * @param returnType
     * @param mavContainer
     * @param webRequest
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        writeWithMessageConverters(returnValue, returnType, webRequest);
    }
}
