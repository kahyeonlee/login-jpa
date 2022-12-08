package com.example.loginjpa.controller;

import com.example.loginjpa.domain.Hospital;
import com.example.loginjpa.domain.dto.HospitalReadResponse;
import com.example.loginjpa.domain.dto.ReviewReadResponse;
import com.example.loginjpa.service.HospitalService;
import com.example.loginjpa.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospitals")
@Slf4j
@RequiredArgsConstructor
public class HospitalController {

    private final ReviewService reviewService;
    private final HospitalService hospitalService;

    @GetMapping("/{hospitalId}/reviews")
    public ResponseEntity<List<ReviewReadResponse>> reviews(@PathVariable Long hospitalId) {
        return ResponseEntity.ok().body(reviewService.findAllByHospitalId(hospitalId));
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<HospitalReadResponse> get(@PathVariable Long hospitalId) {
        Hospital hospital = hospitalService.findById(hospitalId);
        HospitalReadResponse response = HospitalReadResponse.fromEntity(hospital);
        return ResponseEntity.ok().body(response);
    }
}
