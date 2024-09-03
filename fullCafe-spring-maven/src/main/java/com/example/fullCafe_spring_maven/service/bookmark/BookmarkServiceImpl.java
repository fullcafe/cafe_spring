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
import java.util.List;
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다"));
        Cafe cafe = cafeRepository.findById(cafeName)
                .orElseThrow(() -> new CafeNotFoundException("카페를 찾을 수 없습니다"));

        Optional<Bookmark> existingBookmark = bookmarkRepository.findByUserAndCafe(user, cafe);

        if (existingBookmark.isPresent()) {
            throw new IllegalArgumentException("이미 이 카페를 북마크하셨습니다.");
        }

        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setCafe(cafe);

        bookmark.setCreatedAt(LocalDateTime.now());
        bookmark.setUpdatedAt(LocalDateTime.now());

        user.getBookmarks().add(bookmark);
        cafe.getBookmarks().add(bookmark);



        bookmarkRepository.save(bookmark);
        userRepository.save(user);
        cafeRepository.save(cafe);
    }

    @Override
    public List<Bookmark> getBookmarksByUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다"));
        return bookmarkRepository.findByUser(user);
    }

    @Override
    public List<Bookmark> getBookmarksByCafe(String cafeName) {
        Cafe cafe = cafeRepository.findById(cafeName)
                .orElseThrow(() -> new CafeNotFoundException("카페를 찾을 수 없습니다"));
        return bookmarkRepository.findByCafe(cafe);
    }
}
