package com.example.fullCafe_spring_maven.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "server_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @GeneratedValue
    @Id
    private int id;
    @Column(nullable = false)
    private String uid;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false)
    private int characterIdx;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", characterIdx=" + characterIdx +
                '}';
    }
}
