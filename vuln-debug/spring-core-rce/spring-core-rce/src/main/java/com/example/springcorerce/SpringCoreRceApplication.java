package com.example.springcorerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@RestController
public class SpringCoreRceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCoreRceApplication.class, args);
    }

    @RequestMapping(value = "/index")
    public String index(User user){
        System.out.println(user.getName());
        System.out.println(user.getAge());
        return "SpingMvcTest.";
    }
}
