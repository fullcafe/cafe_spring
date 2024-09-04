package com.example.fullCafe_spring_maven.model.dto.visit;

import com.example.fullCafe_spring_maven.model.Visit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleVisitDto {
    @NotNull
    private String uid;
    @NotNull
    private String cafeName;
    @Min(1)
    private int count;
    private boolean writeReview;
    @NotNull
    private LocalDateTime recent;

    public SimpleVisitDto(Visit visit){
        this.uid = visit.getUid();
        this.cafeName = visit.getCafeName();
        this.count = visit.getCount();
        this.writeReview = visit.isWriteReview();
        this.recent = visit.getRecent();
    }
}
