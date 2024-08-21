package com.example.fullCafe_spring_maven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity(name = "server_user")
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

    public User() {
    }

    public User(int id, String uid, String email, String name, LocalDate birthday, int characterIdx) {
        this.id = id;
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.characterIdx = characterIdx;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getCharacterIdx() {
        return characterIdx;
    }

    public void setCharacterIdx(int characterIdx) {
        this.characterIdx = characterIdx;
    }

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
