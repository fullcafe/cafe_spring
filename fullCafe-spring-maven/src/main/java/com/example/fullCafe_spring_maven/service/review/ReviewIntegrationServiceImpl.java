package com.example.fullCafe_spring_maven.service.review;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.Review;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.dto.cafe.SimpleCafeDto;
import com.example.fullCafe_spring_maven.model.dto.review.ComplexReviewDto;
import com.example.fullCafe_spring_maven.model.dto.review.SimpleReviewDto;
import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import com.example.fullCafe_spring_maven.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewIntegrationServiceImpl implements ReviewIntegrationService {
    private final ReviewService reviewService;
    private final UserService userService;
    private final CafeService cafeService;


    public void createReview(SimpleReviewDto reviewDto) {
        User user = userService.findUserByUid(reviewDto.getUid());
        Cafe cafe = cafeService.findCafeByCafeName(reviewDto.getCafeName());
        Review review = Review.builder()
                .numOfStar(reviewDto.getNumOfStar())
                .who(reviewDto.getWho())
                .convenient(reviewDto.getConvenient())
                .object(reviewDto.getObject())
                .content(reviewDto.getContent())
                .timestamp(reviewDto.getTimestamp())
                .user(user)
                .cafe(cafe)
                .build();
        reviewService.createReview(review);
    }

    public List<ComplexReviewDto> findReviewsByUser(String uid){
        User user = userService.findUserByUid(uid);
        List<Review> reviews = user.getReviews();
        List<ComplexReviewDto> reviewDtos = new ArrayList<ComplexReviewDto>();
        if(reviews != null){
            reviews.forEach(review -> {
                SimpleReviewDto reviewDto = new SimpleReviewDto(review);
                SimpleCafeDto cafeDto = new SimpleCafeDto(review.getCafe());
                ComplexReviewDto complexReviewDto = ComplexReviewDto.builder()
                        .uid(uid)
                        .name(user.getName())
                        .reviewDto(reviewDto)
                        .cafeDto(cafeDto)
                        .build();
                reviewDtos.add(complexReviewDto);
            });
        }
        return reviewDtos;
    }
}
