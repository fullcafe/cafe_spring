package com.example.fullCafe_spring_maven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private int id;
    private int numOfStar;
    private List<String> who;
    private List<String> convenient;
    private List<String> object;
    private String content;
    @ManyToOne
    private User user;
    @ManyToOne
    private Cafe cafe;

    public Review() {
    }

    public Review(int id, int numOfStar, List<String> who, List<String> convenient, List<String> object, String content) {
        this.id = id;
        this.numOfStar = numOfStar;
        this.who = who;
        this.convenient = convenient;
        this.object = object;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfStar() {
        return numOfStar;
    }

    public void setNumOfStar(int numOfStar) {
        this.numOfStar = numOfStar;
    }

    public List<String> getWho() {
        return who;
    }

    public void setWho(List<String> who) {
        this.who = who;
    }

    public List<String> getConvenient() {
        return convenient;
    }

    public void setConvenient(List<String> convenient) {
        this.convenient = convenient;
    }

    public List<String> getObject() {
        return object;
    }

    public void setObject(List<String> object) {
        this.object = object;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", numOfStar=" + numOfStar +
                ", who=" + who +
                ", convenient=" + convenient +
                ", object=" + object +
                ", content='" + content + '\'' +
                '}';
    }
}
