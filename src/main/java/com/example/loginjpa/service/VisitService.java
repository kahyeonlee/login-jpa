package com.example.loginjpa.service;

import com.example.loginjpa.domain.Hospital;
import com.example.loginjpa.domain.User;
import com.example.loginjpa.domain.Visit;
import com.example.loginjpa.domain.dto.VisitCreateRequest;
import com.example.loginjpa.exception.ErrorCode;
import com.example.loginjpa.exception.HospitalReviewAppException;
import com.example.loginjpa.repository.HospitalRepository;
import com.example.loginjpa.repository.UserRepository;
import com.example.loginjpa.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public void createVisit(VisitCreateRequest dto, String userName) {

        // hospital이 없을 때 등록불가
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.NOT_FOUNDED, String.format("hospitalId:%s 가 없습니다.", dto.getHospitalId())));

        // user가 없을 때 등록불가
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.USER_NOT_FOUNDED, String.format("%s user가 없습니다.", userName)));

        // 저장
        Visit visit = Visit.builder()
                .user(user)
                .hospital(hospital)
                .disease(dto.getDisease())
                .medicalExpenses(dto.getMedicalExpenses())
                .build();
        visitRepository.save(visit);
    }
}
