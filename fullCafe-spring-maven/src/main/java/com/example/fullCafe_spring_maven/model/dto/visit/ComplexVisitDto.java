package com.example.fullCafe_spring_maven.model.dto.visit;

import com.example.fullCafe_spring_maven.model.dto.cafe.SimpleCafeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplexVisitDto {
    private String uid;
    private String name;
    private SimpleCafeDto cafeDto;
    private SimpleVisitDto visitDto;
}
