package com.example.loginjpa.domain.dto;

import com.example.loginjpa.domain.User;
import com.example.loginjpa.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;

    public User toEntity(String password){
        return User.builder()
                .userName(this.userName)
                .password(password)
                .email(this.email)
                .role(UserRole.USER)
                .build();
    }
}
