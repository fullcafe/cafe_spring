package com.example.fullCafe_spring_maven.controller.visit;

import com.example.fullCafe_spring_maven.model.dto.visit.ComplexVisitDto;
import com.example.fullCafe_spring_maven.model.dto.visit.SimpleVisitDto;
import com.example.fullCafe_spring_maven.service.visit.VisitIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Visit",description = "Controller with Visit")
public class VisitController {
    private final VisitIntegrationService visitIntegrationService;

    @PostMapping("/visit")
    @Operation(summary = "create",description = "create visit")
    public ResponseEntity<SimpleVisitDto> createVisit(@Valid @RequestBody SimpleVisitDto visitDto){
        visitIntegrationService.createVisit(visitDto);
        return new ResponseEntity<SimpleVisitDto>(visitDto, HttpStatus.CREATED);
    }
    @GetMapping("/visits/all/{uid}")
    @Operation(summary = "retrieve",description = "retrieve all visit by user")
    public List<ComplexVisitDto> findAllVisitByUser(@PathVariable String uid){
        return visitIntegrationService.findAllVisitByUser(uid);
    }
    @GetMapping("/visits/no_review/{uid}")
    @Operation(summary = "retrieve",description = "retrieve no review visit by user")
    public List<ComplexVisitDto> findNoReviewVisitByUser(@PathVariable String uid){
        return visitIntegrationService.findNoReviewVisitByUser(uid);
    }
    @GetMapping("/visits/most_visit/{uid}")
    @Operation(summary = "retrieve",description = "retrieve most count visit by user")
    public List<ComplexVisitDto> findMostCountVisitByUser(@PathVariable String uid){
        return visitIntegrationService.findMostCountVisitByUser(uid);
    }

}
