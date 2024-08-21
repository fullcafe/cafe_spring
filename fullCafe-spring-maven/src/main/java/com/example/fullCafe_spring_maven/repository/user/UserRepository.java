package com.example.fullCafe_spring_maven.repository.user;

import com.example.fullCafe_spring_maven.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
