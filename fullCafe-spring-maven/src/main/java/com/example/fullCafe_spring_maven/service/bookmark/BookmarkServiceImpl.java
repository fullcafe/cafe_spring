package com.example.fullCafe_spring_maven.service.bookmark;

import com.example.fullCafe_spring_maven.model.Bookmark;
import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.repository.bookmark.BookmarkRepository;
import com.example.fullCafe_spring_maven.repository.cafe.CafeRepository;
import com.example.fullCafe_spring_maven.repository.user.UserRepository;
import com.example.fullCafe_spring_maven.service.cafe.CafeNotFoundException;
import com.example.fullCafe_spring_maven.service.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CafeRepository cafeRepository;

    @Override
    public void addBookmark(String userId, String cafeName) {
        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다"));

        // 카페 조회
        Cafe cafe = cafeRepository.findById(cafeName)
                .orElseThrow(() -> new CafeNotFoundException("카페를 찾을 수 없습니다"));

        // 기존 북마크 확인
        Optional<Bookmark> existingBookmark = bookmarkRepository.findByUserAndCafe(user, cafe);

        if (existingBookmark.isPresent()) {
            // 이미 유저가 이 카페를 북마크한 경우, 추가 작업 없이 반환
            throw new IllegalArgumentException("이미 이 카페를 북마크하셨습니다.");
        }

        // 새로운 북마크 생성
        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setCafe(cafe);
        bookmark.setCreatedAt(LocalDateTime.now());
        bookmark.setUpdatedAt(LocalDateTime.now());

        // 사용자와 카페의 북마크 리스트에 추가
        user.getBookmarks().add(bookmark);
        cafe.getBookmarks().add(bookmark);

        // 북마크 저장
        bookmarkRepository.save(bookmark);

        // 사용자와 카페 엔터티 저장 (북마크 리스트 업데이트)
        userRepository.save(user);
        cafeRepository.save(cafe);
    }
}
