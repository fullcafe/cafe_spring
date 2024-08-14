package com.example.fullCafe_spring_maven.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 테스트용 컨트롤러
@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }

}
