package com.example.fullCafe_spring_maven.service.cafe;

import com.example.fullCafe_spring_maven.model.Cafe;

public interface CafeService {
    public Cafe findCafeByCafeName(String cafeName);
}
