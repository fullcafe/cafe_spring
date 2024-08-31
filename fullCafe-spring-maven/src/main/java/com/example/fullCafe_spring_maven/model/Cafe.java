package com.example.fullCafe_spring_maven.model;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(unique = true,nullable = false)
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
    @OneToMany(mappedBy = "cafeName")
    private List<CafeKeyword> keywords;
    @OneToMany(mappedBy = "cafe")
    private List<Review> reviews;

    @Override
    public String toString() {
        return "Cafe{" +
                "cafeName='" + name + '\'' +
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
