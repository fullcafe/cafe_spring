package com.example.fullCafe_spring_maven.repository.review;

import com.example.fullCafe_spring_maven.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
