package com.example.fullCafe_spring_maven.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
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
    @JoinColumn(name = "cafeName", insertable = false, updatable = false) // join column 설정
    @JsonBackReference // Cafe로의 참조를 역방향으로 설정
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
