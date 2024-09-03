package com.example.fullCafe_spring_maven.controller.review;

import com.example.fullCafe_spring_maven.firebase.FirebaseAuthentication;
import com.example.fullCafe_spring_maven.model.Review;
import com.example.fullCafe_spring_maven.model.dto.review.ComplexReviewDto;
import com.example.fullCafe_spring_maven.model.dto.review.SimpleReviewDto;
import com.example.fullCafe_spring_maven.security.SpringSecurityConfigurationTest;
import com.example.fullCafe_spring_maven.service.cafe.CafeNotFoundException;
import com.example.fullCafe_spring_maven.service.review.ReviewIntegrationService;
import com.example.fullCafe_spring_maven.service.user.UserNotFoundException;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(ReviewController.class)
@Import(SpringSecurityConfigurationTest.class)
class ReviewControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ReviewIntegrationService reviewIntegrationService;
    @Autowired
    private ObjectMapper mapper;
    private final Authentication authentication = new FirebaseAuthentication("test@mail","uid",true);


    private final Review review = Review.builder()
            .numOfStar(3)
            .who(List.of("누구랑"))
            .convenient(List.of("편의시설"))
            .object(List.of("목적"))
            .content("내용")
            .timestamp(LocalDateTime.now())
            .build();
    @Test
    @DisplayName("리뷰 생성 - 컨트롤러")
    void createReview() throws Exception {
        // given uid, cafe 정보가 잘못되면 예외 반환
        Mockito.doThrow(new UserNotFoundException("유저를 찾을 수 없습니다"))
                .when(reviewIntegrationService)
                .createReview(Mockito.argThat(arg -> !arg.getUid().equals("uid")));
        Mockito.doThrow(new CafeNotFoundException("카페를 찾을 수 없습니다"))
                .when(reviewIntegrationService)
                .createReview(Mockito.argThat(arg -> !arg.getCafeName().equals("cafe")));
        // 성공 uid : uid, cafe : cafe
        SimpleReviewDto reviewDto = new SimpleReviewDto(review);
        reviewDto.setCafeName("cafe"); reviewDto.setUid("uid");
        String content = mapper.writeValueAsString(reviewDto);
        mvc.perform(post("/review")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());
        // 요청이 valid 안함
        SimpleReviewDto reviewDto1 = new SimpleReviewDto(review);
        reviewDto1.setCafeName("cafe"); // 유저 정보가 비었다면?
        String content1 = mapper.writeValueAsString(reviewDto1);
        mvc.perform(post("/review")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content1))
                .andExpect(status().isBadRequest());
        // 유저 없음
        SimpleReviewDto reviewDto2 = new SimpleReviewDto(review);
        reviewDto2.setCafeName("cafe"); reviewDto2.setUid("이상한 값");
        String content2 = mapper.writeValueAsString(reviewDto2);
        mvc.perform(post("/review")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content2))
                .andExpect(status().isNotFound());
        // 카페 없음
        SimpleReviewDto reviewDto3 = new SimpleReviewDto(review);
        reviewDto3.setCafeName("이상한 카페"); reviewDto3.setUid("uid");
        String content3 = mapper.writeValueAsString(reviewDto3);
        mvc.perform(post("/review")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content3))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("유저 기반 리뷰 조회 - 컨트롤러")
    void retrieveReviewsByUser() throws Exception {
        SimpleReviewDto reviewDto = new SimpleReviewDto(review);
        ComplexReviewDto complexDto = ComplexReviewDto.builder()
                .uid("uid")
                .reviewDto(reviewDto)
                .build();
        Mockito.when(reviewIntegrationService.findReviewsByUser("uid")).thenReturn(
                List.of(complexDto, complexDto)
        );
        Mockito.when(reviewIntegrationService.findReviewsByUser(Mockito.argThat(arg->!arg.equals("uid"))))
                .thenThrow(new UserNotFoundException("유저를 찾을 수 없습니다."));
        // 유저 못가져옴
        mvc.perform(get("/reviews/strange")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
                .andExpect(status().isNotFound());
        // 리뷰가 든 리스트를 가져옴(성공)
        mvc.perform(get("/reviews/uid")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].['uid']").value("uid"))
                .andExpect(jsonPath("$[1].['reviewDto'].['numOfStar']").value(3));
    }

}