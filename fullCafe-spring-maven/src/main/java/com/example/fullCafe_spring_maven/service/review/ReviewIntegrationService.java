package com.example.fullCafe_spring_maven.service.review;

import com.example.fullCafe_spring_maven.model.dto.review.SimpleReviewDto;

public interface ReviewIntegrationService {
    public void createReview(SimpleReviewDto reviewDto);
}
