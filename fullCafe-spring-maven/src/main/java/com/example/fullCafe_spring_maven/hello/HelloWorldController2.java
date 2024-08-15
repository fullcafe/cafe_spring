package com.example.fullCafe_spring_maven.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController2 {

    @GetMapping("/hello/2")
    public String getHello2(){
        return "hello";
    }
}
