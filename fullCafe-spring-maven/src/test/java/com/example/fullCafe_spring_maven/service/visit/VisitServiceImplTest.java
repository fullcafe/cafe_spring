package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.model.Visit;
import com.example.fullCafe_spring_maven.repository.visit.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class VisitServiceImplTest {
    private VisitService visitService;
    @MockBean
    private VisitRepository visitRepository;

    @BeforeEach
    void setUp(){
        visitService = new VisitServiceImpl(visitRepository);
    }

    @Test
    @DisplayName("visit 생성 - 서비스")
    void createVisit(){
        // given
        visitService.createVisit(new Visit());
        // when
        verify(visitRepository,times(1)).save(new Visit());
    }

}