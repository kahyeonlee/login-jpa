package com.example.loginjpa.domain.dto;

import com.example.loginjpa.domain.User;
import com.example.loginjpa.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private UserRole userRole;

    public static UserDto fromEntity(User entity) {
        return new UserDto(entity.getId(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getRole());
    }
}