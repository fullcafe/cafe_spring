package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.model.dto.visit.SimpleVisitDto;

public interface VisitIntegrationService {
    void createVisit(SimpleVisitDto visitDto);
}
