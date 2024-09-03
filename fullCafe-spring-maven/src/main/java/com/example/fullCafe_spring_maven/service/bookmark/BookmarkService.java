package com.example.fullCafe_spring_maven.service.bookmark;

import com.example.fullCafe_spring_maven.model.Bookmark;

import java.util.List;

public interface BookmarkService {
    void addBookmark(String userId, String cafeName);
    // 유저별 북마크 조회
    List<Bookmark> getBookmarksByUser(String userId);
    // 카페별 북마크 조회
    List<Bookmark> getBookmarksByCafe(String cafeName);
}
