package com.example.loginjpa.controller;

import com.example.loginjpa.domain.dto.UserJoinRequest;
import com.example.loginjpa.domain.dto.UserJoinResponse;
import com.example.loginjpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto) {
        return ResponseEntity.ok().body("등록이 성공하였습니다.");
    }
}
