package com.example.fullCafe_spring_maven.service.review;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.Review;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.dto.review.SimpleReviewDto;
import com.example.fullCafe_spring_maven.model.dto.user.ResponseSimpleUserDto;
import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import com.example.fullCafe_spring_maven.service.user.UserNotFoundException;
import com.example.fullCafe_spring_maven.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReviewIntegrationServiceImplTest {
    private ReviewIntegrationService reviewIntegrationService;
    @MockBean
    private ReviewService reviewService;
    @MockBean
    private UserService userService;
    @MockBean
    private CafeService cafeService;

    private final User user = User.builder()
            .uid("uid")
            .email("email")
            .name("name")
            .birthday(LocalDate.now())
            .characterIdx(0)
            .build();
    private final Cafe cafe = Cafe.builder()
            .name("cafe")
            .address("address")
            .phone("12345")
            .build();
    private final Review review = Review.builder()
            .numOfStar(3)
            .who(List.of("누구랑"))
            .convenient(List.of("편의시설"))
            .object(List.of("목적"))
            .content("내용")
            .user(user)
            .cafe(cafe)
            .build();

    @BeforeEach
    void setup(){
        reviewIntegrationService = new ReviewIntegrationServiceImpl(
                reviewService,userService,cafeService
        );
    }
    @Test
    void createReview() {
        //given
        SimpleReviewDto reviewDto = new SimpleReviewDto(review);
        Mockito.when(userService.findByUid(user.getUid())).thenReturn(new ResponseSimpleUserDto(user));
        Mockito.when(userService.findByUid(Mockito.argThat(uid -> !uid.equals(user.getUid()))))
                .thenThrow(new UserNotFoundException("유저를 찾을 수 없음"));
        //when
        reviewIntegrationService.createReview(reviewDto);
    }
}
/*
 카페 들고옴, 유저 들고옴 -> 여기서 못들고올 가능성
 들고온 것 기반으로 크레이트
 dto 2개 (심플한 dto) (심플dto,카페심플dto,유저심플dto)

 유저 id -> 전부 다 들고옴
 uid랑 리뷰 리스트만 보내주면 됨
 리뷰 알갱이 및 카페 정보까지 필요함
 dto 1개(uid,리스트)

 카페 id -> 전부 다 들고옴
 카페정보 및 리뷰 2개 정도
 리뷰 조회하면 카페 정보 및 리뷰 여러개
 각각의 리뷰는 알갱이 및 유저 정보
 dto 2개(카페정보, 리뷰 2개) (카페간소정보, 리뷰 전부)
 */