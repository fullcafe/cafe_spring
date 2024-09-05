package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.Visit;

import java.util.List;

public interface FindVisitCallBack {
    List<Visit> findVisit(User user);
}
