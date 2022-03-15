package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IndexController {
    @RequestMapping(value = "/index")
    @GetMapping()
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("spring index controller.");
    }
}


