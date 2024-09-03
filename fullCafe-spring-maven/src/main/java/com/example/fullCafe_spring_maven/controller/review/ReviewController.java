package com.example.fullCafe_spring_maven.controller.review;

import com.example.fullCafe_spring_maven.model.dto.review.ComplexReviewDto;
import com.example.fullCafe_spring_maven.model.dto.review.SimpleReviewDto;
import com.example.fullCafe_spring_maven.service.review.ReviewIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Review",description = "Controller with Review")
public class ReviewController {
    private final ReviewIntegrationService reviewIntegrationService;
    @PostMapping("/review")
    @Operation(summary = "create",description = "create review")
    public ResponseEntity<SimpleReviewDto> createReview(@Valid @RequestBody SimpleReviewDto reviewDto){
        reviewIntegrationService.createReview(reviewDto);
        return new ResponseEntity<SimpleReviewDto>(reviewDto, HttpStatus.CREATED);
    }

    @GetMapping("/reviews/user/{uid}")
    @Operation(summary = "retrieve",description = "retrieve reviews by user")
    public List<ComplexReviewDto> retrieveReviewsByUser(@PathVariable String uid){
        return reviewIntegrationService.findReviewsByUser(uid);
    }

    @GetMapping("/reviews/cafe/{cafeName}")
    @Operation(summary = "retrieve", description = "retrieve reviews by cafe")
    public List<ComplexReviewDto> retrieveReviewsByCafe(@PathVariable String cafeName){
        return reviewIntegrationService.findReviewsByCafe(cafeName);
    }
}
