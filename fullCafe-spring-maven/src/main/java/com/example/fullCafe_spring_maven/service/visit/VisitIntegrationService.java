package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.model.Visit;
import com.example.fullCafe_spring_maven.model.dto.visit.ComplexVisitDto;
import com.example.fullCafe_spring_maven.model.dto.visit.SimpleVisitDto;

import java.util.List;

public interface VisitIntegrationService {
    void createVisit(SimpleVisitDto visitDto);
    List<ComplexVisitDto> convertVisitToComplexDto(List<Visit> visits, String uid, String username);
    List<ComplexVisitDto> findAllVisitByUser(String uid);
    List<ComplexVisitDto> findMostCountVisitByUser(String uid);
}
