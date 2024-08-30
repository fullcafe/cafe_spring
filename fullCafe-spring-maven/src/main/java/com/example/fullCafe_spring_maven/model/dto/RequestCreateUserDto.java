package com.example.fullCafe_spring_maven.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateUserDto {
    private String uid;
    private String email;
    @NotNull
    private String name;
    @NotNull
    private LocalDate birthday;
    @NotNull
    private Integer characterIdx;

}
