package com.example.fullCafe_spring_maven.service.cafe;

import com.example.fullCafe_spring_maven.model.Cafe;

import java.util.List;

public interface CafeService {
    Cafe findCafeByName(String name);
    List<Cafe> searchCafesByFilters(String name, Boolean wifi, Boolean petFriendly, Boolean takeout,
                                           Boolean groupFriendly, Boolean parking, Boolean easyPayment,
                                           Boolean delivery, List<String> keywords);
//    List<Cafe> getCafesSortedByRating(String name, Boolean wifi, Boolean petFriendly, Boolean takeout,
//                                      Boolean groupFriendly, Boolean parking, Boolean easyPayment,
//                                      Boolean delivery);
}
