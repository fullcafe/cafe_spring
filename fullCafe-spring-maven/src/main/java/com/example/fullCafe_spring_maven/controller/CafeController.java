package com.example.fullCafe_spring_maven.controller;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cafes")
public class CafeController {

    @Autowired
    private CafeService cafeService;

    @GetMapping("/search")
    public List<Cafe> searchCafes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean wifi,
            @RequestParam(required = false) Boolean petFriendly,
            @RequestParam(required = false) Boolean takeout,
            @RequestParam(required = false) Boolean groupFriendly,
            @RequestParam(required = false) Boolean parking,
            @RequestParam(required = false) Boolean easyPayment,
            @RequestParam(required = false) Boolean delivery,
            @RequestParam(required = false) String keywords) {

        // 콤마로 구분된 키워드 문자열을 리스트로 변환
        List<String> keywordList = (keywords != null && !keywords.isEmpty()) ?
                Arrays.asList(keywords.split(",")) : null;

        List<Cafe> cafes = cafeService.searchCafesByFilters(
                name, wifi, petFriendly, takeout, groupFriendly, parking, easyPayment, delivery, keywordList);
        return cafes;
    }

    @GetMapping("/{cafeName}")
    public ResponseEntity<Cafe> getCafeByName(@PathVariable String cafeName) {
        Cafe cafe = cafeService.findCafesByName(cafeName);
        return ResponseEntity.ok(cafe);
        }
}
