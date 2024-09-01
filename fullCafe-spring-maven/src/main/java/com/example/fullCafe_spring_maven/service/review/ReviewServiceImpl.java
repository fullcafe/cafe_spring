package com.example.fullCafe_spring_maven.service.review;

import com.example.fullCafe_spring_maven.model.Review;
import com.example.fullCafe_spring_maven.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    public void createReview(Review review){
        reviewRepository.save(review);
    }

}
