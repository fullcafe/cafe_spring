package com.example.fullCafe_spring_maven.service.user;
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg){ super(msg); }
}
