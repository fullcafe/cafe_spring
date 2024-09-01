package com.example.fullCafe_spring_maven.model.dto.user;

import com.example.fullCafe_spring_maven.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseSimpleUserDto {
    private String uid;
    private String email;
    private String name;
    private LocalDate birthday;
    private Integer characterIdx;

    public ResponseSimpleUserDto(User user){
        this.uid = user.getUid();
        this.email = user.getEmail();
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.characterIdx = user.getCharacterIdx();
    }
}
