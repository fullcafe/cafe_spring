package com.example.fullCafe_spring_maven.service.user;

import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user){
        userRepository.save(user);
    }

    public User findByUid(String uid) {
        Optional<User> user = userRepository.findByUid(uid);
        if(user.isEmpty()){
            throw new UserNotFoundException("유저를 찾을 수 없습니다.");
        }

        return user.get();
    }
}
