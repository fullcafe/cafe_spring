package com.example.fullCafe_spring_maven.service.user;

import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.dto.user.RequestCreateUserDto;
import com.example.fullCafe_spring_maven.model.dto.user.ResponseSimpleUserDto;

public interface UserService {
    public void createUser(RequestCreateUserDto requestCreateUserDto);
    public User findUserByUid(String uid);
    public ResponseSimpleUserDto findSimpleUserByUid(String uid);
}
