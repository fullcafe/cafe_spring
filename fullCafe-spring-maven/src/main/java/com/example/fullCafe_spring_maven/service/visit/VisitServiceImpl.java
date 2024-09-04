package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.model.Visit;
import com.example.fullCafe_spring_maven.repository.visit.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;

    public void createVisit(Visit visit){
        visitRepository.save(visit);
    }
}
