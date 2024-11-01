package com.example.fullCafe_spring_maven.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Cafe {
    @Id
    @Column(unique = true, nullable = false)
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

//    @Column(precision = 2, scale = 1)
//    private Double rating;

//    @Column
//    private Double latitude;
//
//    @Column
//    private Double longitude;

    @OneToMany(mappedBy = "cafe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference // 순환 참조 방지 설정
    private List<CafeKeyword> keywords = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visit> visits = new ArrayList<>();

    @Override
    public String toString() {
        return "Cafe{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", url='" + url + '\'' +
                ", petFriendly=" + petFriendly +
                ", wifi=" + wifi +
                ", takeout=" + takeout +
                ", groupFriendly=" + groupFriendly +
                ", easyPayment=" + easyPayment +
                ", parking=" + parking +
                ", delivery=" + delivery +

                '}';
    }
}
