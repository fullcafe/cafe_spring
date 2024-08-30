package com.example.fullCafe_spring_maven.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cafe {
    @Id
    @Column(unique = true,nullable = false)
    private String cafeName;
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
    private List<CafeKeyword> keyword;

    public Cafe() {
    }

    public Cafe(String cafeName, String address, String phone, String url, boolean petFriendly, boolean wifi, boolean takeout, boolean groupFriendly, boolean easyPayment, boolean parking, boolean delivery) {
        this.cafeName = cafeName;
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

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
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

    public List<CafeKeyword> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<CafeKeyword> keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "cafeName='" + cafeName + '\'' +
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
