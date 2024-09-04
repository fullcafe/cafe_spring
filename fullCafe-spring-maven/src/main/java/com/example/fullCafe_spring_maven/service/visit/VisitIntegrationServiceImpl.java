package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.Visit;
import com.example.fullCafe_spring_maven.model.dto.visit.SimpleVisitDto;
import com.example.fullCafe_spring_maven.model.key.VisitId;
import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import com.example.fullCafe_spring_maven.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VisitIntegrationServiceImpl implements VisitIntegrationService{
    private final VisitService visitService;
    private final UserService userService;
    private final CafeService cafeService;

    public void createVisit(SimpleVisitDto visitDto) {
        User user = userService.findUserByUid(visitDto.getUid());
        Cafe cafe = cafeService.findCafeByCafeName(visitDto.getCafeName());
        VisitId visitId = VisitId.builder()
                .uid(user.getUid())
                .cafeName(cafe.getName())
                .build();
        Visit visit = Visit.builder()
                .visitId(visitId)
                .count(visitDto.getCount())
                .recent(visitDto.getRecent())
                .build();
        visitService.createVisit(visit);
    }
}
