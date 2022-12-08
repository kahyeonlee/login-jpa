package com.example.loginjpa.service;

import com.example.loginjpa.domain.User;
import com.example.loginjpa.domain.UserRole;
import com.example.loginjpa.domain.dto.UserDto;
import com.example.loginjpa.exception.AppException;
import com.example.loginjpa.exception.ErrorCode;
import com.example.loginjpa.repository.UserRepository;
import com.example.loginjpa.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs= 1000*60*60l;

    public String join(String userName,String password){

        //중복 체크
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, userName + "은 이미 있습니다.");
                });

        //저장
        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .role(UserRole.USER)
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }

    public String login(String userName, String password) {
        //username 없음
        User selectedUser = userRepository.findByUserName(userName)
                .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND,userName+"이 없습니다."));

        //password 틀림
        if(!encoder.matches(password,selectedUser.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력하셨습니다.");
        }

        //앞에서 Exception안나면 토큰 발행
        String token = JwtTokenUtil.createToken(selectedUser.getUserName(), key, expireTimeMs);
        return token;
    }


    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, ""));
    }
}
