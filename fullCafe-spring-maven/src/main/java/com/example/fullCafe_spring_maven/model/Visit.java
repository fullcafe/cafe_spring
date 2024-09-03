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
    @GeneratedValue
    private int id;
    private int count; // 횟수 디폴트 0
    private boolean writeReview; // 리뷰 작성 여부 디폴트 F
    @Column(nullable = false)
    private LocalDateTime recent; // 최근 방문 시간
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cafeName", nullable = false)
    private Cafe cafe;

}
