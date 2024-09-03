package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.repository.visit.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class VisitServiceImplTest {
    private VisitService visitService;
    @MockBean
    private VisitRepository visitRepository;

    @BeforeEach
    void setUp(){
        visitService = new VisitServiceImpl(visitRepository);
    }


}