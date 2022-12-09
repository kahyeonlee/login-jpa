package com.example.loginjpa.domain.dto;

import com.example.loginjpa.domain.Hospital;
import com.example.loginjpa.domain.User;
import com.example.loginjpa.domain.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VisitCreateRequest {

    private Long hospitalId;
    private Long userId;
    private String disease;
    private int medicalExpenses;


}