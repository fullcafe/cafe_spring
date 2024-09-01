package com.example.fullCafe_spring_maven.service.review;

import com.example.fullCafe_spring_maven.model.dto.review.SimpleReviewDto;
import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import com.example.fullCafe_spring_maven.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewIntegrationServiceImpl implements ReviewIntegrationService{
    private final ReviewService reviewService;
    private final UserService userService;
    private final CafeService cafeService;


    public void createReview(SimpleReviewDto reviewDto) {

    }
}
