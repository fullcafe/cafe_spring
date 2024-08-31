package com.example.fullCafe_spring_maven.controller.user;

import com.example.fullCafe_spring_maven.firebase.FirebaseAuthentication;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.dto.RequestCreateUserDto;
import com.example.fullCafe_spring_maven.model.dto.ResponseSimpleUserDto;
import com.example.fullCafe_spring_maven.security.SpringSecurityConfigurationTest;
import com.example.fullCafe_spring_maven.service.user.UserNotFoundException;
import com.example.fullCafe_spring_maven.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(UserController.class)
@Import(SpringSecurityConfigurationTest.class)
class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;
    @Autowired
    private SecurityFilterChain filterChain;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper mapper;

    private final User user = User.builder()
            .uid("uid")
            .email("email")
            .name("name")
            .birthday(LocalDate.now())
            .characterIdx(0)
            .build();
    private final Authentication authentication = new FirebaseAuthentication(user.getEmail(),user.getUid(),true);

    @BeforeEach
    void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("retrieve 유저 - 컨트롤러")
    void retrieveUser() throws Exception {
        Mockito.when(userService.findByUid(user.getUid())).thenReturn(new ResponseSimpleUserDto(user));
        Mockito.when(userService.findByUid(Mockito.argThat(uid -> !uid.equals(user.getUid()))))
                        .thenThrow(new UserNotFoundException("유저를 찾을 수 없음"));
        // 정상적으로 가져옴
        mvc.perform(get("/user")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['uid']").value(user.getUid()));
        // 유저를 찾지 못함
        Authentication fake = new FirebaseAuthentication(user.getEmail(),"이상한 값",true);
        mvc.perform(get("/user")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(fake)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("유저 생성 - 컨트롤러")
    void createUser() throws Exception {
        RequestCreateUserDto userDto = new RequestCreateUserDto(
                user.getUid(), user.getEmail(), user.getName(), user.getBirthday(), user.getCharacterIdx()
        );
        String json = mapper.writeValueAsString(userDto);
        // 정상적으로 생성
        mvc.perform(post("/register")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$['uid']").value(user.getUid()));
        verify(userService,times(1)).createUser(userDto);
        // 정상적으로 생성 안됨(valid 하지 않음)
        // 아무것도 없음
        RequestCreateUserDto userDto2 = new RequestCreateUserDto();
        String json2 = mapper.writeValueAsString(userDto2);

        mvc.perform(post("/register")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json2))
                .andExpect(status().isBadRequest());

        // 하나만 없음
        RequestCreateUserDto userDto3 = new RequestCreateUserDto();
        userDto3.setUid(user.getUid()); userDto3.setEmail(user.getEmail());
        userDto3.setName(user.getName()); userDto3.setBirthday(user.getBirthday());
        String json3 = mapper.writeValueAsString(userDto3);

        mvc.perform(post("/register")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json3))
                .andExpect(status().isBadRequest());
    }
}