package com.example.fullCafe_spring_maven.model;

import com.example.fullCafe_spring_maven.model.key.VisitId;
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
    @EmbeddedId
    private VisitId visitId;
    private int count; // 횟수 디폴트 0
    private boolean writeReview; // 리뷰 작성 여부 디폴트 F
    @Column(nullable = false)
    private LocalDateTime recent; // 최근 방문 시간
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("uid")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cafeName")
    private Cafe cafe;

    @Override
    public String toString() {
        return "Visit{" +
                "visitId=" + visitId +
                ", count=" + count +
                ", writeReview=" + writeReview +
                ", recent=" + recent +
                '}';
    }
}
