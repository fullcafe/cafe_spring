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
    boolean writeReview; // 리뷰 작성 가능 여부 T면 작성 가능
    @Column(nullable = false)
    private LocalDateTime recent; // 최근 방문 시간
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("uid")
    @JoinColumn(name = "uid")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cafeName")
    @JoinColumn(name = "cafe_name")
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
