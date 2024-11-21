package com.example.fullCafe_spring_maven.service.cafe;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.repository.cafe.CafeRepository;
import com.example.fullCafe_spring_maven.service.cafe.CafeNotFoundException;
import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {
    private final CafeRepository cafeRepository;

    // 기존의 findCafeByName 메서드 유지
    public Cafe findCafeByName(String name) {
        Optional<Cafe> cafe = cafeRepository.findById(name);
        if (cafe.isEmpty()) {
            throw new CafeNotFoundException("카페를 찾지 못 했습니다");
        }
        return cafe.get();
    }

    // 필터 조건을 반영한 검색 메서드
    public List<Cafe> searchCafesByFilters(String name, Boolean wifi, Boolean petFriendly, Boolean takeout,
                                           Boolean groupFriendly, Boolean parking, Boolean easyPayment,
                                           Boolean delivery, List<String> keywords) {
        long keywordCount = (keywords != null) ? keywords.size() : 0; // 키워드 수 계산
        return cafeRepository.findByFiltersWithKeywordPriority(
                name, wifi, petFriendly, takeout, groupFriendly,
                parking, easyPayment, delivery, keywords, keywordCount);
    }

}
