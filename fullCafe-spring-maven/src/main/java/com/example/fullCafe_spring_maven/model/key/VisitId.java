package com.example.fullCafe_spring_maven.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class VisitId implements Serializable {
    @Column(nullable = false)
    private String uid;
    @Column(nullable = false)
    private String cafeName;
}
