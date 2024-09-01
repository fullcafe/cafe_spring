package com.example.fullCafe_spring_maven.repository.cafe;

import com.example.fullCafe_spring_maven.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, String> {
}
