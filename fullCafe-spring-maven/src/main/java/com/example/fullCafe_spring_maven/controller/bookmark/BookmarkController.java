package com.example.fullCafe_spring_maven.controller.bookmark;

import com.example.fullCafe_spring_maven.service.bookmark.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @PostMapping("/add")
    public ResponseEntity<String> addBookmark(@RequestParam String userId, @RequestParam String cafeName) {
        try {
            // BookmarkService를 통해 북마크 추가
            bookmarkService.addBookmark(userId, cafeName);
            return ResponseEntity.ok("Bookmark added successfully!");
        } catch (com.example.fullCafe_spring_maven.service.user.UserNotFoundException e) {
            return ResponseEntity.status(404).body("User not found");
        } catch (com.example.fullCafe_spring_maven.service.cafe.CafeNotFoundException e) {
            return ResponseEntity.status(404).body("Cafe not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while adding the bookmark");
        }
    }
}
