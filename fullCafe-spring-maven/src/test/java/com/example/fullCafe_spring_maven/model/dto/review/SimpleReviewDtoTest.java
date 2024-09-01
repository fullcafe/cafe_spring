package com.example.fullCafe_spring_maven.model.dto.review;

import com.example.fullCafe_spring_maven.model.Review;
import com.example.fullCafe_spring_maven.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleReviewDtoTest {

    @Test
    @DisplayName("리뷰 DTO 생성")
    void createDto(){
        // 빈 리뷰 일 때
        Review review = new Review();
        SimpleReviewDto reviewDto = new SimpleReviewDto(review);
        assertEquals(reviewDto.getId(),0);
        assertNull(reviewDto.getObject());
        assertNull(reviewDto.getContent());
        assertNull(reviewDto.getUid());

        // 유저(카페)가 존재할 때
        User user = new User();
        user.setUid("테스트");
        Review review1 = new Review();
        review1.setUser(user);
        SimpleReviewDto reviewDto1 = new SimpleReviewDto(review1);
        assertEquals(reviewDto1.getId(),0);
        assertNull(reviewDto1.getObject());
        assertNull(reviewDto1.getContent());
        assertEquals(reviewDto1.getUid(),"테스트");

    }

}