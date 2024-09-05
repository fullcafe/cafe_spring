package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.Visit;

import java.util.List;

public interface VisitService {
    void createVisit(Visit visit);
    List<Visit> findByUserAndWriteReview(User user, boolean isWrite);
    List<Visit> findByUserAndCountGreaterThanEqual(User user,int count);
}
