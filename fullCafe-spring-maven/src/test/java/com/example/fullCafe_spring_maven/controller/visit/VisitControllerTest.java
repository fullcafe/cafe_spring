package com.example.fullCafe_spring_maven.controller.visit;

import com.example.fullCafe_spring_maven.firebase.FirebaseAuthentication;
import com.example.fullCafe_spring_maven.model.dto.visit.ComplexVisitDto;
import com.example.fullCafe_spring_maven.model.dto.visit.SimpleVisitDto;
import com.example.fullCafe_spring_maven.security.SpringSecurityConfigurationTest;
import com.example.fullCafe_spring_maven.service.user.UserNotFoundException;
import com.example.fullCafe_spring_maven.service.visit.VisitIntegrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(VisitController.class)
@Import(SpringSecurityConfigurationTest.class)
class VisitControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private VisitIntegrationService visitIntegrationService;
    @Autowired
    private ObjectMapper mapper;
    private final Authentication authentication = new FirebaseAuthentication("test@mail","uid",true);

    @Test
    @DisplayName("visit 생성 테스트 - 컨트롤러")
    void createVisit() throws Exception {
        SimpleVisitDto visitDto = SimpleVisitDto.builder()
                .uid("uid")
                .cafeName("cafe")
                .count(1)
                .recent(LocalDateTime.now())
                .build();
        Mockito.doThrow(new UserNotFoundException("유저 없음"))
                .when(visitIntegrationService)
                .createVisit(Mockito.argThat(arg->!arg.getUid().equals("uid")));
        // 생성
        String content = mapper.writeValueAsString(visitDto);
        mvc.perform(post("/visit")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());
        // 유저(카페) not found
        visitDto.setUid("이상한 값");
        String content1 = mapper.writeValueAsString(visitDto);
        mvc.perform(post("/visit")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content1))
                .andExpect(status().isNotFound());
        // valid 하지 않음(null값, int 조건에 안맞음)
        visitDto.setCount(0);
        String content2 = mapper.writeValueAsString(visitDto);
        mvc.perform(post("/visit")
                .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content2))
                .andExpect(status().isBadRequest());
        visitDto.setCount(1); visitDto.setRecent(null);
        String content3 = mapper.writeValueAsString(visitDto);
        mvc.perform(post("/visit")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content3))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("유저 기반 방문 전부 조회 - 컨트롤러")
    void findAllVisitByUser() throws Exception {
        Mockito.when(visitIntegrationService.findAllVisitByUser("uid")).thenReturn(List.of(
                new ComplexVisitDto()));
        Mockito.when(visitIntegrationService.findAllVisitByUser(Mockito.argThat(arg->!arg.equals("uid"))))
                        .thenThrow(new UserNotFoundException("유저 없음"));
        // 유저 없
        mvc.perform(get("/visits/all/srei")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
                .andExpect(status().isNotFound());
        // 성공
        mvc.perform(get("/visits/all/uid")
                .with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

}