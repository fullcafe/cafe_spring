package com.example.fullCafe_spring_maven.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

    public Cafe() {
    }

    public Cafe(String name, String address, String phone, String url, boolean petFriendly, boolean wifi, boolean takeout, boolean groupFriendly, boolean easyPayment, boolean parking, boolean delivery) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.url = url;
        this.petFriendly = petFriendly;
        this.wifi = wifi;
        this.takeout = takeout;
        this.groupFriendly = groupFriendly;
        this.easyPayment = easyPayment;
        this.parking = parking;
        this.delivery = delivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isTakeout() {
        return takeout;
    }

    public void setTakeout(boolean takeout) {
        this.takeout = takeout;
    }

    public boolean isGroupFriendly() {
        return groupFriendly;
    }

    public void setGroupFriendly(boolean groupFriendly) {
        this.groupFriendly = groupFriendly;
    }

    public boolean isEasyPayment() {
        return easyPayment;
    }

    public void setEasyPayment(boolean easyPayment) {
        this.easyPayment = easyPayment;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public List<CafeKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<CafeKeyword> keywords) {
        this.keywords = keywords;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

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
