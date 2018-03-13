package com.bow.spring.springmvc.viewResolver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

/**
 * {@link org.springframework.ui.ModelMap}数据实体，{@link View}用于展示数据控制着样式等视图信息
 * 
 * @author vv
 * @since 2017/2/2.
 */
public class DemoHtmlView implements View {
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("render view");
        String head = "<html><head><title>Hello World</title></head><body><ul>";
        String tail = "</ul></body></html>";
        StringBuilder sb = new StringBuilder();
        sb.append(head);
        for (Map.Entry<String, ?> entry : model.entrySet()) {
            sb.append("<li>").append(entry.getKey()).append(":").append(entry.getValue()).append("</li>");
        }
        sb.append(tail);
        response.getWriter().write(sb.toString());
    }
}
