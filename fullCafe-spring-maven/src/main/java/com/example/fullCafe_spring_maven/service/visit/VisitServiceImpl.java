package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.Visit;
import com.example.fullCafe_spring_maven.repository.visit.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;

    public void createVisit(Visit visit){
        visitRepository.save(visit);
    }
    public List<Visit> findByUserAndWriteReview(User user,boolean isWrite){
        return visitRepository.findByUserAndWriteReview(user,isWrite);
    }
    public List<Visit> findByUserAndCountGreaterThanEqual(User user, int count){
        return visitRepository.findByUserAndCountGreaterThanEqual(user,count);
    }
}
