package com.example.fullCafe_spring_maven.service.user;

import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.dto.user.RequestCreateUserDto;
import com.example.fullCafe_spring_maven.model.dto.user.ResponseSimpleUserDto;
import com.example.fullCafe_spring_maven.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public void createUser(RequestCreateUserDto requestCreateUserDto){
        User user = User.builder()
                .uid(requestCreateUserDto.getUid())
                .email(requestCreateUserDto.getEmail())
                .name(requestCreateUserDto.getName())
                .birthday(requestCreateUserDto.getBirthday())
                .characterIdx(requestCreateUserDto.getCharacterIdx())
                .build();
        userRepository.save(user);
    }

    public ResponseSimpleUserDto findByUid(String uid) {
        Optional<User> user = userRepository.findById(uid);
        if(user.isEmpty()){
            throw new UserNotFoundException("유저를 찾을 수 없습니다.");
        }
        User gettedUser = user.get();
        return new ResponseSimpleUserDto(gettedUser);
    }
}
