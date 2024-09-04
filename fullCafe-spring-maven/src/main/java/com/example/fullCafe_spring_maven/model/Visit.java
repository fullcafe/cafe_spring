package com.example.fullCafe_spring_maven.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Visit {
    @Id
    @Column(nullable = false)
    private String uid;
    @Id
    @Column(nullable = false)
    private String cafeName;
    private int count; // 횟수 디폴트 0
    private boolean writeReview; // 리뷰 작성 여부 디폴트 F
    @Column(nullable = false)
    private LocalDateTime recent; // 최근 방문 시간
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cafeName")
    private Cafe cafe;

    @Override
    public String toString() {
        return "Visit{" +
                "uid='" + uid + '\'' +
                ", cafeName='" + cafeName + '\'' +
                ", count=" + count +
                ", writeReview=" + writeReview +
                ", recent=" + recent +
                '}';
    }
}
