package com.example.fullCafe_spring_maven.controller.user;

import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@Valid @RequestBody User user, Authentication auth){
        user.setEmail(auth.getName());
        user.setUid((String)auth.getPrincipal());
        userService.createUser(user);
        return user;
    }
}
