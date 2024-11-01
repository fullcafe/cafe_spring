package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.Visit;
import com.example.fullCafe_spring_maven.model.dto.visit.ComplexVisitDto;
import com.example.fullCafe_spring_maven.model.dto.visit.SimpleVisitDto;
import com.example.fullCafe_spring_maven.model.key.VisitId;
import com.example.fullCafe_spring_maven.service.cafe.CafeNotFoundException;
import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import com.example.fullCafe_spring_maven.service.user.UserNotFoundException;
import com.example.fullCafe_spring_maven.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class VisitIntegrationServiceImplTest {
    private VisitIntegrationService visitIntegrationService;
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

    @BeforeEach
    void setUp(){
        visitIntegrationService = new VisitIntegrationServiceImpl(
                visitService,userService,cafeService
        );
    }

    @Test
    @DisplayName("Visit 통합 생성 - 서비스")
    void createVisit(){
        Mockito.when(userService.findUserByUid(user.getUid())).thenReturn(user);
        Mockito.when(userService.findUserByUid(Mockito.argThat(arg->!arg.equals(user.getUid()))))
                .thenThrow(new UserNotFoundException("유저 없음"));
        Mockito.when(cafeService.findCafeByName(cafe.getName())).thenReturn(cafe);
        Mockito.when(cafeService.findCafeByName(Mockito.argThat(arg->!arg.equals(cafe.getName()))))
                .thenThrow(new CafeNotFoundException("카페 없음"));
        // 유저 not found
        SimpleVisitDto visitDto = new SimpleVisitDto(visit);
        visitDto.setUid("이상한 값");
        assertThrows(UserNotFoundException.class,()->{
            visitIntegrationService.createVisit(visitDto);
        });
        // 카페 not found
        SimpleVisitDto visitDto1 = new SimpleVisitDto(visit);
        visitDto1.setCafeName("이상한 값");
        assertThrows(CafeNotFoundException.class,()->{
            visitIntegrationService.createVisit(visitDto1);
        });
        // 서비스 호출
        SimpleVisitDto visitDto2 = new SimpleVisitDto(visit);
        visitIntegrationService.createVisit(visitDto2);
        verify(visitService,times(1)).createVisit(visit);
    }
    @Test
    @DisplayName("visit 전부 조회 - 서비스")
    void findAllVisitByUser(){
        Mockito.when(userService.findUserByUid(user.getUid())).thenReturn(user);
        Mockito.when(userService.findUserByUid(Mockito.argThat(arg->!arg.equals(user.getUid()))))
                .thenThrow(new UserNotFoundException("유저 없음"));
        // 유저 없
        assertThrows(UserNotFoundException.class,()->{
            visitIntegrationService.findAllVisitByUser("sdasd");
        });
        // 빈리스트
        List<ComplexVisitDto> visitDtos = visitIntegrationService.findAllVisitByUser(user.getUid());
        assertEquals(visitDtos,List.of());
        // 성공
        user.setVisits(List.of(visit));
        List<ComplexVisitDto> visitDtos1 = visitIntegrationService.findAllVisitByUser(user.getUid());
        assertEquals(visitDtos1.get(0).getUid(),user.getUid());
        assertEquals(visitDtos1.get(0).getVisitDto().getCount(),1);
        assertEquals(visitDtos1.get(0).getCafeDto().getName(),cafe.getName());
    }

}