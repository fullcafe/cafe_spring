package com.example.fullCafe_spring_maven.service.cafe;

import com.example.fullCafe_spring_maven.repository.cafe.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {
    private final CafeRepository cafeRepository;
}
