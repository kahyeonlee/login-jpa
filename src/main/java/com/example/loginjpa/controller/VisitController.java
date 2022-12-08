package com.example.loginjpa.controller;

import com.example.loginjpa.domain.dto.VisitCreateRequest;
import com.example.loginjpa.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vi/visits")
@Slf4j
@RequiredArgsConstructor
public class VisitController {
    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<String> createVisitLog(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication) {
        String userName = authentication.getName();
        log.info("userName:{}", userName);
        visitService.createVisit(visitCreateRequest, userName);
        return ResponseEntity.ok().body("방문 기록이 등록 되었습니다.");
    }


//    GET /api/v1/visits → 전체 조회
//    GET /api/v1/visits/users/{id} → 특정 user의 기록 조회
//    GET /api/v1/visits/hospitals/{id} → 특정 병원의 기록 조회


}
