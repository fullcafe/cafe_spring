package com.example.fullCafe_spring_maven.repository.visit;

import com.example.fullCafe_spring_maven.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit,Integer> {
}
