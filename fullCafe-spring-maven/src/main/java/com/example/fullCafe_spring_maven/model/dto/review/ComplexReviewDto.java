package com.example.fullCafe_spring_maven.model.dto.review;

import com.example.fullCafe_spring_maven.model.dto.cafe.SimpleCafeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplexReviewDto {
    // 유저 uid 및 이름 정보
    private String uid;
    private String name;
    // 카페 심플 정보
    private SimpleCafeDto cafeDto;
    // 리뷰 심플 정보
    private SimpleReviewDto reviewDto;
}
