package com.example.fullCafe_spring_maven.service.user;

import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user){
        userRepository.save(user);
    }

}
