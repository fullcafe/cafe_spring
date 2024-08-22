package com.example.fullCafe_spring_maven.service.user;
public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String msg){ super(msg); }
}
