package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import com.example.fullCafe_spring_maven.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class VisitIntegrationServiceImplTest {
    private VisitIntegrationService visitIntegrationService;
    @MockBean
    private UserService userService;
    @MockBean
    private CafeService cafeService;
    @MockBean
    private VisitService visitService;

    @BeforeEach
    void setUp(){
        visitIntegrationService = new VisitIntegrationServiceImpl(
                visitService,userService,cafeService
        );
    }

}