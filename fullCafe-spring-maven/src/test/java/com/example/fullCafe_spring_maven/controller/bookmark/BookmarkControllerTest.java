package com.example.fullCafe_spring_maven.controller.bookmark;

import com.example.fullCafe_spring_maven.firebase.FirebaseAuthentication;
import com.example.fullCafe_spring_maven.security.SpringSecurityConfigurationTest;
import com.example.fullCafe_spring_maven.service.bookmark.BookmarkService;
import com.example.fullCafe_spring_maven.service.cafe.CafeNotFoundException;
import com.example.fullCafe_spring_maven.service.user.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookmarkController.class)
@Import(SpringSecurityConfigurationTest.class)
class BookmarkControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookmarkService bookmarkService;
    @Autowired
    private ObjectMapper mapper;

    private final Authentication authentication = new FirebaseAuthentication("test@mail.com", "uid", true);

    @Test
    @DisplayName("북마크 추가 - 컨트롤러")
    void addBookmark() throws Exception {
        // 성공적인 북마크 추가
        mvc.perform(post("/api/bookmarks/add")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .param("userId", "uid")
                        .param("cafeName", "cafe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // 유저를 찾을 수 없는 경우
        Mockito.doThrow(new UserNotFoundException("유저를 찾을 수 없습니다"))
                .when(bookmarkService)
                .addBookmark("invalid_uid", "cafe");

        mvc.perform(post("/api/bookmarks/add")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .param("userId", "invalid_uid")
                        .param("cafeName", "cafe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        // 카페를 찾을 수 없는 경우
        Mockito.doThrow(new CafeNotFoundException("카페를 찾을 수 없습니다"))
                .when(bookmarkService)
                .addBookmark("uid", "invalid_cafe");

        mvc.perform(post("/api/bookmarks/add")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
                        .param("userId", "uid")
                        .param("cafeName", "invalid_cafe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
