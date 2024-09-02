package com.example.fullCafe_spring_maven.model.dto.cafe;

import com.example.fullCafe_spring_maven.model.Cafe;
import com.example.fullCafe_spring_maven.model.CafeKeyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleCafeDto {
    private String name;
    private String address;
    private String phone;
    private String url;
    private boolean petFriendly;
    private boolean wifi;
    private boolean takeout;
    private boolean groupFriendly;
    private boolean easyPayment;
    private boolean parking;
    private boolean delivery;
    private List<String> keywords;

    public SimpleCafeDto(Cafe cafe){
        this.name = cafe.getName();
        this.address = cafe.getAddress();
        this.phone = cafe.getPhone();
        this.url = cafe.getUrl();
        this.petFriendly = cafe.isPetFriendly();
        this.wifi = cafe.isWifi();
        this.takeout = cafe.isTakeout();
        this.groupFriendly = cafe.isGroupFriendly();
        this.easyPayment = cafe.isEasyPayment();
        this.parking = cafe.isParking();
        this.delivery = cafe.isDelivery();
        if(cafe.getKeywords() != null){
            this.keywords = new ArrayList<String>();
            cafe.getKeywords().forEach(
                    cafeKeyword -> this.keywords.add(cafeKeyword.getKeyword())
            );
        }
    }
}
