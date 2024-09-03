package com.example.fullCafe_spring_maven.repository.bookmark;

import com.example.fullCafe_spring_maven.model.Bookmark;
import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByUserAndCafe(User user, Cafe cafe);

    // 유저별 북마크 리스트 조회
    List<Bookmark> findByUser(User user);

    // 카페별 북마크 리스트 조회
    List<Bookmark> findByCafe(Cafe cafe);
}
