package com.example.fullCafe_spring_maven.service.user;

import com.example.fullCafe_spring_maven.model.dto.RequestCreateUserDto;
import com.example.fullCafe_spring_maven.model.dto.ResponseSimpleUserDto;

public interface UserService {
    public void createUser(RequestCreateUserDto requestCreateUserDto);
    public ResponseSimpleUserDto findByUid(String uid);
}
