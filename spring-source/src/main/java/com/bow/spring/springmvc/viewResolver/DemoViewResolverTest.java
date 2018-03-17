package com.bow.spring.springmvc.viewResolver;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 自定义视图解析器{@link DemoViewResolver}
 *
 * @see DemoController
 * @see DemoHtmlView
 * @see DemoHandlerMethodReturnValueHandler
 * @author vv
 * @since 2017/2/2.
 */
@Configuration
public class DemoViewResolverTest extends WebMvcConfigurationSupport {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        DemoViewResolver myViewResolver = new DemoViewResolver();
        registry.viewResolver(myViewResolver);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new DemoHandlerMethodReturnValueHandler());
    }

    @Bean
    public DemoController demoController() {
        return new DemoController();
    }


    @Bean
    public DemoHtmlView demoHtmlView() {
        return new DemoHtmlView();
    }


    public void test() throws ServletException, IOException {
        MockServletContext mockServletContext = new MockServletContext();
        MockServletConfig mockServletConfig = new MockServletConfig(mockServletContext);
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(DemoViewResolverTest.class);
        context.setServletConfig(mockServletConfig);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        dispatcherServlet.init(mockServletConfig);

        // mock request
        MockHttpServletResponse htmlResponse = new MockHttpServletResponse();
        MockHttpServletRequest htmlRequest = new MockHttpServletRequest("GET", "/html");
        dispatcherServlet.service(htmlRequest, htmlResponse);
        System.out.println(new String(htmlResponse.getContentAsByteArray()));
    }
}
