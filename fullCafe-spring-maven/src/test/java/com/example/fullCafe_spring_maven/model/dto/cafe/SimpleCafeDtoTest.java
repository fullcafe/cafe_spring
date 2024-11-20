package com.example.fullCafe_spring_maven.model.dto.cafe;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.CafeKeyword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCafeDtoTest {
    CafeKeyword keyword1 = CafeKeyword.builder()
            .cafeName("cafe")
            .keyword("keyword1")
            .build();
    CafeKeyword keyword2 = CafeKeyword.builder()
            .cafeName("cafe")
            .keyword("keyword2")
            .build();

    Cafe cafe1 = Cafe.builder()
            .name("cafe")
            .petFriendly(true)
            .keywords(List.of(keyword1,keyword2))
            .build();
    Cafe cafe2 = Cafe.builder()
            .keywords(List.of(keyword1))
            .build();

    @Test
    @DisplayName("심플 카페 DTO 생성")
    void createDto(){
        // 잘 생성 됨(각 타입별)
        SimpleCafeDto cafeDto = new SimpleCafeDto(cafe1);
        assertEquals(cafeDto.getName(),"cafe");
        assertTrue(cafeDto.isPetFriendly());
        assertEquals(cafeDto.getKeywords(),List.of("keyword1","keyword2"));
        // 키워드가 1개
        SimpleCafeDto cafeDto1 = new SimpleCafeDto(cafe2);
        assertEquals(cafeDto1.getKeywords(),List.of("keyword1"));
        // 키워드가 0개
        Cafe cafe3 = new Cafe(); cafe3.setKeywords(List.of());
        SimpleCafeDto cafeDto3 = new SimpleCafeDto(cafe3);
        assertEquals(cafeDto3.getKeywords(),List.of());
    }

}