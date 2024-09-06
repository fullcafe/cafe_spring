package com.example.fullCafe_spring_maven.service.review;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.Review;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.Visit;
import com.example.fullCafe_spring_maven.model.dto.cafe.SimpleCafeDto;
import com.example.fullCafe_spring_maven.model.dto.review.ComplexReviewDto;
import com.example.fullCafe_spring_maven.model.dto.review.SimpleReviewDto;
import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import com.example.fullCafe_spring_maven.service.user.UserService;
import com.example.fullCafe_spring_maven.service.visit.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewIntegrationServiceImpl implements ReviewIntegrationService {
    private final ReviewService reviewService;
    private final UserService userService;
    private final CafeService cafeService;
    private final VisitService visitService;

    public void writeReview(SimpleReviewDto reviewDto, User user, Cafe cafe){
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

    public void createReview(SimpleReviewDto reviewDto) {
        // 리뷰 작성 가능 여부 체크
        User user = userService.findUserByUid(reviewDto.getUid());
        Cafe cafe = cafeService.findCafeByCafeName(reviewDto.getCafeName());
        Visit visit = visitService.findByUserAndCafe(user,cafe);
        if(!visit.isWriteReview()){
            throw new ReviewWriteException("리뷰를 작성할 수 없습니다.");
        }
        // 리뷰 작성 및 권한 업데이트
        visit.setWriteReview(false);
        writeReview(reviewDto,user,cafe);
        visitService.createVisit(visit);
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

    public List<ComplexReviewDto> findReviewsByCafe(String cafeName){
        Cafe cafe = cafeService.findCafeByCafeName(cafeName);
        List<Review> reviews = cafe.getReviews();
        List<ComplexReviewDto> reviewDtos = new ArrayList<ComplexReviewDto>();
        if(reviews != null){
            reviews.forEach(review -> {
                SimpleReviewDto reviewDto = new SimpleReviewDto(review);
                ComplexReviewDto complexReviewDto = ComplexReviewDto.builder()
                        .reviewDto(reviewDto)
                        .build();
                User user = review.getUser();
                // 유저는 nullable = false지만 DB 오류로 null일 때를 방지
                if(user != null){
                    complexReviewDto.setUid(user.getUid());
                    complexReviewDto.setName(user.getName());
                }
                reviewDtos.add(complexReviewDto);
            });
        }
        return reviewDtos;
    }

}
