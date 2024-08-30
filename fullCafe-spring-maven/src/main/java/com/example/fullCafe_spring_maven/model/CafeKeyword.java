package com.example.fullCafe_spring_maven.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CafeKeyword {
    @Id
    @Column(nullable = false)
    private String keyword;
    @Id
    @Column(nullable = false)
    private String cafeName;
    @Column(nullable = false)
    private int frequency;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cafeName")
    private Cafe cafe;

    @Override
    public String toString() {
        return "CafeKeyword{" +
                "keyword='" + keyword + '\'' +
                ", cafeName='" + cafeName + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
