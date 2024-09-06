package com.example.fullCafe_spring_maven.service.review;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.Review;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.Visit;
import com.example.fullCafe_spring_maven.model.dto.cafe.SimpleCafeDto;
import com.example.fullCafe_spring_maven.model.dto.review.ComplexReviewDto;
import com.example.fullCafe_spring_maven.model.dto.review.SimpleReviewDto;
import com.example.fullCafe_spring_maven.model.key.VisitId;
import com.example.fullCafe_spring_maven.service.cafe.CafeNotFoundException;
import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import com.example.fullCafe_spring_maven.service.user.UserNotFoundException;
import com.example.fullCafe_spring_maven.service.user.UserService;
import com.example.fullCafe_spring_maven.service.visit.VisitNotFoundException;
import com.example.fullCafe_spring_maven.service.visit.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ReviewIntegrationServiceImplTest {
    private ReviewIntegrationService reviewIntegrationService;
    @MockBean
    private ReviewService reviewService;
    @MockBean
    private UserService userService;
    @MockBean
    private CafeService cafeService;
    @MockBean
    private VisitService visitService;

    private final User user = User.builder()
            .uid("uid")
            .email("email")
            .name("name")
            .birthday(LocalDate.now())
            .characterIdx(0)
            .build();
    private final Cafe cafe = Cafe.builder()
            .name("cafe")
            .address("address")
            .phone("12345")
            .build();

    private final VisitId visitId = VisitId.builder()
            .uid(user.getUid())
            .cafeName(cafe.getName())
            .build();
    private final Visit visit = Visit.builder()
            .visitId(visitId)
            .count(1)
            .writeReview(true)
            .recent(LocalDateTime.now())
            .user(user)
            .cafe(cafe)
            .build();

    private final Review review = Review.builder()
            .numOfStar(3)
            .who(List.of("누구랑"))
            .convenient(List.of("편의시설"))
            .object(List.of("목적"))
            .content("내용")
            .timestamp(LocalDateTime.now())
            .user(user)
            .cafe(cafe)
            .build();

    @BeforeEach
    void setup(){
        reviewIntegrationService = new ReviewIntegrationServiceImpl(
                reviewService,userService,cafeService,visitService
        );
    }

    @Test
    @DisplayName("리뷰 통합 생성 - 서비스")
    void createReview() {
        //given
        SimpleReviewDto reviewDto = new SimpleReviewDto(review);
        Mockito.when(userService.findUserByUid(user.getUid())).thenReturn(user);
        Mockito.when(cafeService.findCafeByCafeName(cafe.getName())).thenReturn(cafe);
        Mockito.when(visitService.findByUserAndCafe(user,cafe)).thenReturn(visit);
        // 제대로 리뷰 만듦
        reviewIntegrationService.createReview(reviewDto);
        verify(reviewService,times(1)).createReview(review);
        // 리뷰 작성 권한 없을 시 예외
        visit.setWriteReview(false);
        assertThrows(ReviewWriteException.class,()->{
            reviewIntegrationService.createReview(reviewDto);
        });
    }

    @Test
    @DisplayName("유저로 부터 리뷰 조회 - 서비스")
    void findReviewsByUser(){
        // given
        Mockito.when(userService.findUserByUid(user.getUid())).thenReturn(user);
        Mockito.when(userService.findUserByUid(Mockito.argThat(uid -> !uid.equals(user.getUid()))))
                .thenThrow(new UserNotFoundException("유저를 찾을 수 없음"));
        // 유저를 못 들고옴
        assertThrows(UserNotFoundException.class,()->{
            reviewIntegrationService.findReviewsByUser("이상한 값");
        });
        // 리뷰가 없음(빈 리스트를 들고옴)
        List<ComplexReviewDto> reviewDtos = reviewIntegrationService.findReviewsByUser(user.getUid());
        assertEquals(reviewDtos,List.of());
        // 성공(complexDto 리스트를 들고옴)
        user.setReviews(List.of(review));
        List<ComplexReviewDto> reviewDtos1 = reviewIntegrationService.findReviewsByUser(user.getUid());
        ComplexReviewDto complexReviewDto = ComplexReviewDto.builder()
                .uid(user.getUid())
                .name(user.getName())
                .reviewDto(new SimpleReviewDto(review))
                .cafeDto(new SimpleCafeDto(cafe))
                .build();
        assertEquals(reviewDtos1,List.of(complexReviewDto));
    }

    @Test
    @DisplayName("카페로 부터 리뷰 조회 - 서비스")
    void findReviewByCafe(){
        // given
        Mockito.when(cafeService.findCafeByCafeName(cafe.getName())).thenReturn(cafe);
        Mockito.when(cafeService.findCafeByCafeName(Mockito.argThat(arg-> !arg.equals(cafe.getName()))))
                .thenThrow(new CafeNotFoundException("카페를 찾을수 없음"));

        // 카페 못들고옴(not found)
        assertThrows(CafeNotFoundException.class,()->{
            reviewIntegrationService.findReviewsByCafe("이상한 카페");
        });
        // 리뷰 못들오곰(빈리스트)
        List<ComplexReviewDto> reviewDtos = reviewIntegrationService.findReviewsByCafe(cafe.getName());
        assertEquals(reviewDtos,List.of());
        // 유저 못 들고옴(유저 정보 null)
        review.setUser(null);
        cafe.setReviews(List.of(review));
        List<ComplexReviewDto> reviewDtos1 = reviewIntegrationService.findReviewsByCafe(cafe.getName());
        assertNull(reviewDtos1.get(0).getUid());
        assertNull(reviewDtos1.get(0).getName());
        assertNotNull(reviewDtos1.get(0).getReviewDto());
        // 성공
        review.setUser(user);
        List<ComplexReviewDto> reviewDtos2 = reviewIntegrationService.findReviewsByCafe(cafe.getName());
        assertEquals(reviewDtos2.get(0).getUid(),user.getUid());
        assertEquals(reviewDtos2.get(0).getName(),user.getName());
        assertEquals(reviewDtos2.get(0).getReviewDto(),new SimpleReviewDto(review));
        assertEquals(reviewDtos2.size(),1);

    }

}
/*
 */