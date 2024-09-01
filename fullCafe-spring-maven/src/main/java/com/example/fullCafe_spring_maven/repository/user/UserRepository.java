package com.example.fullCafe_spring_maven.repository.user;

import com.example.fullCafe_spring_maven.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
}
