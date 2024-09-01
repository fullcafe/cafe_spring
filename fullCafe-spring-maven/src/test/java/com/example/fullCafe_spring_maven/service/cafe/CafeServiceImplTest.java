package com.example.fullCafe_spring_maven.service.cafe;

import com.example.fullCafe_spring_maven.repository.cafe.CafeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class CafeServiceImplTest {
    private CafeService cafeService;
    @MockBean
    private CafeRepository cafeRepository;

    @BeforeEach
    void setup(){
        cafeService = new CafeServiceImpl(cafeRepository);
    }
}