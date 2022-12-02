package com.example.loginjpa.controller;

import com.example.loginjpa.domain.dto.UserJoinRequest;
import com.example.loginjpa.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jshell.Snippet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void join_success() throws Exception {

        String userName = "hi";
        String password = "123456";

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password))))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    void join_fail() throws Exception {
        String userName = "hi";
        String password = "123456";

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password))))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().isConflict());

    }
}