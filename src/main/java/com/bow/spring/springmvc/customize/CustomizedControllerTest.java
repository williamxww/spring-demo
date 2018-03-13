package com.bow.spring.springmvc.customize;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 自定义
 * {@link MyRequestMappingHandlerMapping}其中包括自定义{@link MyController},{@link MyRequestMapping}
 * @author vv
 * @since 2017/2/3.
 */
@Configuration
public class CustomizedControllerTest extends WebMvcConfigurationSupport {
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = super.requestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> converters = new ArrayList();
        converters.add(new MyHttpMessageConvert());

        // argumentResolvers
        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList();
        argumentResolvers.add(new ArgumentAndReturnValueResolver(converters));
        requestMappingHandlerAdapter.setCustomArgumentResolvers(argumentResolvers);

        // returnValueHandlers
        List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList();
        returnValueHandlers.add(new ArgumentAndReturnValueResolver(converters));
        requestMappingHandlerAdapter.setCustomReturnValueHandlers(returnValueHandlers);
        return requestMappingHandlerAdapter;
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        MyRequestMappingHandlerMapping handlerMapping = new MyRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        handlerMapping.setContentNegotiationManager(mvcContentNegotiationManager());

        PathMatchConfigurer configurer = getPathMatchConfigurer();
        if (configurer.isUseSuffixPatternMatch() != null) {
            handlerMapping.setUseSuffixPatternMatch(configurer.isUseSuffixPatternMatch());
        }
        if (configurer.isUseRegisteredSuffixPatternMatch() != null) {
            handlerMapping.setUseRegisteredSuffixPatternMatch(configurer.isUseRegisteredSuffixPatternMatch());
        }
        if (configurer.isUseTrailingSlashMatch() != null) {
            handlerMapping.setUseTrailingSlashMatch(configurer.isUseTrailingSlashMatch());
        }
        if (configurer.getPathMatcher() != null) {
            handlerMapping.setPathMatcher(configurer.getPathMatcher());
        }
        if (configurer.getUrlPathHelper() != null) {
            handlerMapping.setUrlPathHelper(configurer.getUrlPathHelper());
        }

        return handlerMapping;
    }

    @Bean
    public CustomizedController customizedController() {
        return new CustomizedController();
    }

    /**
     * 注意： 这里用的是application/vson VSON，发送的contentType和期望接收accept都是这个
     *
     * @see MyHttpMessageConvert
     * @throws Exception e
     */
    public void test() throws Exception {
        // init environment
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(CustomizedControllerTest.class);
        MockServletContext mockServletContext = new MockServletContext();
        MockServletConfig mockServletConfig = new MockServletConfig(mockServletContext);
        context.setServletConfig(mockServletConfig);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        dispatcherServlet.init(mockServletConfig);

        // 注意： 这里用的是application/vson VSON，发送的contentType和期望接收accept都是这个
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/customize");
        request.addHeader("Accept", "application/vson");
        request.setContentType("application/vson");
        request.setContent(("name:vv;date:" + Calendar.getInstance().getTimeInMillis()).getBytes());
        dispatcherServlet.service(request, response);
        System.out.println(">>>" + new String(response.getContentAsByteArray()));
    }

}
