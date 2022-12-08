package com.example.loginjpa.controller;

import com.example.loginjpa.domain.dto.UserJoinRequest;
import com.example.loginjpa.domain.dto.UserLoginRequest;
import com.example.loginjpa.exception.AppException;
import com.example.loginjpa.exception.ErrorCode;
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
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
    @WithMockUser
    void join_success() throws Exception {

        String userName = "hi";
        String password = "123456";
        String email = "kahyun@gmail.com";


        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password,email))))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    @WithMockUser
    void join_fail() throws Exception {
        String userName = "hi";
        String password = "123456";
        String email = "kahyun@gmail.com";

        when(userService.join(any(), any()))
                .thenThrow(new RuntimeException("해당 userId가 중복됩니다."));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password,email))))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().isConflict());

    }
    @Test
    @DisplayName("로그인 -성공")
    @WithMockUser
    void login_success() throws Exception {
        String userName = "hi";
        String password = "123456";

        when(userService.login(any(), any()))
                .thenReturn("token");


        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("로그인 실패- userName 없음")
    @WithMockUser
    void login_fail1() throws Exception {
        String userName = "hi";
        String password = "123456";

        when(userService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("로그인 실패 -password 틀림")
    @WithMockUser
    void login_fail2() throws Exception {
        String userName = "hi";
        String password = "123456";

        when(userService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, ""));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))))
                .andDo(print()) //요청, 응답 전체메세지 확인
                .andExpect(status().isUnauthorized());
    }

}