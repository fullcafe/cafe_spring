package com.example.fullCafe_spring_maven.model;

import jakarta.persistence.*;

@Entity
public class CafeKeyword {
    @Id
    @Column(nullable = false)
    private String keyword;
    @Id
    @Column(nullable = false)
    private String cafeName;
    @Column(nullable = false)
    private int frequency;
    @ManyToOne
    @JoinColumn(name = "cafeName")
    private Cafe cafe;

    public CafeKeyword() {
    }

    public CafeKeyword(String keyword, String cafeName, int frequency) {
        this.keyword = keyword;
        this.cafeName = cafeName;
        this.frequency = frequency;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    @Override
    public String toString() {
        return "CafeKeyword{" +
                "keyword='" + keyword + '\'' +
                ", cafeName='" + cafeName + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
