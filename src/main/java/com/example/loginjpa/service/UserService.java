package com.example.loginjpa.service;

import com.example.loginjpa.domain.User;
import com.example.loginjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String join(String userName,String password){

        //중복 체크
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new RuntimeException(userName+"은 이미 있습니다.");
                });

        //저장
        User user = User.builder()
                .userName(userName)
                .password(password)
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }
}
