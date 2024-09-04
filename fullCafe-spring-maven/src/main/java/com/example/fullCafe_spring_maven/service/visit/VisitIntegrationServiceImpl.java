package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.User;
import com.example.fullCafe_spring_maven.model.Visit;
import com.example.fullCafe_spring_maven.model.dto.cafe.SimpleCafeDto;
import com.example.fullCafe_spring_maven.model.dto.visit.ComplexVisitDto;
import com.example.fullCafe_spring_maven.model.dto.visit.SimpleVisitDto;
import com.example.fullCafe_spring_maven.model.key.VisitId;
import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import com.example.fullCafe_spring_maven.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
                .user(user)
                .cafe(cafe)
                .build();
        visitService.createVisit(visit);
    }

    public List<ComplexVisitDto> convertVisitToComplexDto(List<Visit> visits,String uid,String username){
        List<ComplexVisitDto> visitDtos = new ArrayList<ComplexVisitDto>();
        visits.forEach(visit -> {
            Cafe cafe = visit.getCafe();
            ComplexVisitDto complexVisitDto = ComplexVisitDto.builder()
                    .uid(uid)
                    .name(username)
                    .cafeDto(new SimpleCafeDto(cafe))
                    .visitDto(new SimpleVisitDto(visit))
                    .build();
            visitDtos.add(complexVisitDto);
        });
        return visitDtos;
    }

    public List<ComplexVisitDto> findAllVisitByUser(String uid){
        User user = userService.findUserByUid(uid);
        List<Visit> visits = user.getVisits();
        List<ComplexVisitDto> visitDtos = new ArrayList<ComplexVisitDto>();
        if(visits != null){
            visitDtos = convertVisitToComplexDto(visits,user.getUid(),user.getName());
        }
        return visitDtos;
    }
    public List<ComplexVisitDto> findNoReviewVisitByUser(String uid){
        User user = userService.findUserByUid(uid);
        List<Visit> visits = visitService.findNoReviewVisitByUser(user);
        List<ComplexVisitDto> visitDtos = new ArrayList<ComplexVisitDto>();
        if(visits != null){
            visitDtos = convertVisitToComplexDto(visits,user.getUid(),user.getName());
        }
        return visitDtos;
    }
}
