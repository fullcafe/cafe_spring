package com.example.fullCafe_spring_maven.hello;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 테스트용 컨트롤러
@RestController
@Tag(name = "Hello", description = "Swagger-ui Test")
public class HelloWorldController {
    @Operation(summary = "Get Hello", description = "Test API")
    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }

}
