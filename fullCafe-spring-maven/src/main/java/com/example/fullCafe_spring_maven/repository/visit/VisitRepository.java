package com.example.fullCafe_spring_maven.repository.visit;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.Visit;
import com.example.fullCafe_spring_maven.model.key.VisitId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, VisitId> {
    List<Visit> findByUserAndWriteReview(User user,boolean writeReview);
    List<Visit> findByUserAndCountGreaterThanEqual(User user,int count);
    Optional<Visit> findByUserAndCafe(User user, Cafe cafe);
}
