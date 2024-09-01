package com.example.fullCafe_spring_maven.service.review;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.Review;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.repository.review.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ReviewServiceImplTest {

    private ReviewService reviewService;
    @MockBean
    private ReviewRepository reviewRepository;

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
        reviewService = new ReviewServiceImpl(reviewRepository);
    }

    @Test
    @DisplayName("리뷰 생성 - 서비스")
    void createReview(){
        //when
        reviewService.createReview(review);
        //then
        verify(reviewRepository,times(1)).save(review);
    }
}