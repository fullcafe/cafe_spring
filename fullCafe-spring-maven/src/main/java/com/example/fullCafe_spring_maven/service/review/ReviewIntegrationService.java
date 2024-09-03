package com.example.fullCafe_spring_maven.service.review;

import com.example.fullCafe_spring_maven.model.dto.review.ComplexReviewDto;
import com.example.fullCafe_spring_maven.model.dto.review.SimpleReviewDto;

import java.util.List;

public interface ReviewIntegrationService {
    public void createReview(SimpleReviewDto reviewDto);
    public List<ComplexReviewDto> findReviewsByUser(String uid);
    public List<ComplexReviewDto> findReviewsByCafe(String cafeName);
}
