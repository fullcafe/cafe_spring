package com.example.fullCafe_spring_maven.service.cafe;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.repository.cafe.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {
    private final CafeRepository cafeRepository;

    public Cafe findCafeByCafeName(String cafeName){
        Optional<Cafe> cafe = cafeRepository.findById(cafeName);
        if(cafe.isEmpty()){
            throw new CafeNotFoundException("카페를 찾지 못 했습니다");
        }
        return cafe.get();
    }

}
