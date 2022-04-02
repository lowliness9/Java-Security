package com.deno;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.CachedIntrospectionResults;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(User user){
        System.out.println(user.getName());
        return "okok";
    }

    public static void main(String[] args) {

    }




}
