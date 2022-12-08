package com.example.loginjpa.domain.dto;

import com.example.loginjpa.domain.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class VisitCreateResponse {

    private Long hospitalId;
    private Long userId;
    private String disease;
    private int medicalExpenses;

    public static VisitCreateResponse of(Visit visit) {
        return VisitCreateResponse.builder()
                .userId(visit.getUser().getId())
                .hospitalId(visit.getHospital().getId())
                .disease(visit.getDisease())
                .medicalExpenses(visit.getMedicalExpenses())
                .build();
    }
}