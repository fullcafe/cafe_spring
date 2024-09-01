package com.example.fullCafe_spring_maven.service.cafe;

import com.example.fullCafe_spring_maven.repository.cafe.CafeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("카페 이름으로 카페 조회 - 서비스")
    void findCafeByCafeName(){
        // 카페 이름을 먼저 받음
        // 카페가 없으면 예외 발생
        // 카페가 존재하면 들고옴 dto형태로
    }
}