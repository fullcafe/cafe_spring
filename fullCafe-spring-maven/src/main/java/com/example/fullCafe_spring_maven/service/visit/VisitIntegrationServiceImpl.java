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
                .writeReview(visitDto.isWriteReview())
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

    public List<ComplexVisitDto> findByUserTemplate(String uid,FindVisitCallBack callBack){
        User user = userService.findUserByUid(uid);
        List<Visit> visits = callBack.findVisit(user);
        List<ComplexVisitDto> visitDtos = new ArrayList<ComplexVisitDto>();
        if(visits != null){
            visitDtos = convertVisitToComplexDto(visits,user.getUid(),user.getName());
        }
        return visitDtos;
    }

    public List<ComplexVisitDto> findAllVisitByUser(String uid){
        return findByUserTemplate(uid, new FindVisitCallBack() {
            @Override
            public List<Visit> findVisit(User user) {
                return user.getVisits();
            }
        });
    }

    public List<ComplexVisitDto> findWriteReviewVisitByUser(String uid) {
        return findByUserTemplate(uid, new FindVisitCallBack() {
            @Override
            public List<Visit> findVisit(User user) {
                return visitService.findByUserAndWriteReview(user,true);
            }
        });
    }

    // 10개 이상의 방문만
    public List<ComplexVisitDto> findMostCountVisitByUser(String uid){
        return findByUserTemplate(uid, new FindVisitCallBack() {
            @Override
            public List<Visit> findVisit(User user) {
                return visitService.findByUserAndCountGreaterThanEqual(user,10);
            }
        });
    }
}
