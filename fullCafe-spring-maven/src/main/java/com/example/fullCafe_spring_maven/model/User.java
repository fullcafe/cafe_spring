package com.example.fullCafe_spring_maven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
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
    private String uid;
    private String email;
    @NotNull
    private String name;
    @NotNull
    private LocalDate birthday;
    @NotNull
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
