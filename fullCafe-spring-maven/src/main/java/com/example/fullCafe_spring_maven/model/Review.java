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
public class Review {
    @Id
    @GeneratedValue
    private int id;
    private int numOfStar;
    private List<String> who;
    private List<String> convenient;
    private List<String> object;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cafe cafe;

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
