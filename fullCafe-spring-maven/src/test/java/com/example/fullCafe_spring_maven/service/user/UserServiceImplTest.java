package com.example.fullCafe_spring_maven.service.user;

import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.dto.RequestCreateUserDto;
import com.example.fullCafe_spring_maven.model.dto.ResponseSimpleUserDto;
import com.example.fullCafe_spring_maven.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    private final User user = User.builder()
            .uid("uid")
            .email("email")
            .name("name")
            .birthday(LocalDate.now())
            .characterIdx(0)
            .build();

    @BeforeEach
    void init(){
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("유저 생성 - 서비스")
    void createUser() {
        // given
        RequestCreateUserDto userDto = new RequestCreateUserDto(
                user.getUid(),user.getEmail(),user.getName(),user.getBirthday(),user.getCharacterIdx()
        );
        // when
        userService.createUser(userDto);
        // then
        verify(userRepository,times(1)).save(user);
    }

    @Test
    @DisplayName("Uid로 유저 찾기 - 서비스")
    void findByUid() {
        // given
        Optional<User> optionalUser = Optional.ofNullable(user);
        Mockito.when(userRepository.findByUid(user.getUid())).thenReturn(optionalUser);
        // uid 존재
        ResponseSimpleUserDto userDto = userService.findByUid(user.getUid());
        assertEquals(userDto, new ResponseSimpleUserDto(user));
        // uid 존재 ㄴㄴ
        assertThrows(UserNotFoundException.class,()->{
            ResponseSimpleUserDto userDto2 = userService.findByUid("아무거나");
        });
        assertThrows(UserNotFoundException.class,()->{
            ResponseSimpleUserDto userDto2 = userService.findByUid(user.getName());
        });
    }
}