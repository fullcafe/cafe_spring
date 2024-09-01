package com.example.fullCafe_spring_maven.model.dto.review;

import com.example.fullCafe_spring_maven.model.Review;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleReviewDto {
    private int id;
    @NotNull
    private int numOfStar;
    @NotNull
    private List<String> who;
    @NotNull
    private List<String> convenient;
    @NotNull
    private List<String> object;
    @NotNull
    private String content;
    @NotNull
    private String uid;
    @NotNull
    private String cafeName;

    public SimpleReviewDto(Review review){
        this.id = review.getId();
        this.numOfStar = review.getNumOfStar();
        this.who = review.getWho();
        this.convenient = review.getConvenient();
        this.object = review.getObject();
        this.content = review.getContent();
        if(review.getUser() != null){
            this.uid = review.getUser().getUid();
        }
        if(review.getCafe() != null){
            this.cafeName = review.getCafe().getName();
        }
    }

}
