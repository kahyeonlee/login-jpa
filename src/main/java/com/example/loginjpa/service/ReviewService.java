package com.example.loginjpa.service;

import com.example.loginjpa.domain.Hospital;
import com.example.loginjpa.domain.Review;
import com.example.loginjpa.domain.dto.ReviewCreateRequest;
import com.example.loginjpa.domain.dto.ReviewCreateResponse;
import com.example.loginjpa.domain.dto.ReviewReadResponse;
import com.example.loginjpa.repository.HospitalRepository;
import com.example.loginjpa.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    public ReviewService(ReviewRepository reviewRepository, HospitalRepository hospitalRepository) {
        this.reviewRepository = reviewRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public ReviewCreateResponse createReview(ReviewCreateRequest dto) {
        //Hospital 불러오기
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(dto.getHospitalId());

        //ReviewEntity만들기
        Review review = Review.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .patientName(dto.getUserName())
                .hospital(hospitalOptional.get())
                .build();
        //저장
        Review savedReview = reviewRepository.save(review);
        //반환
        return new ReviewCreateResponse().builder()
                .title(savedReview.getTitle())
                .content(savedReview.getContent())
                .userName(savedReview.getPatientName())
                .message("리뷰 등록이 성공하였습니다.")
                .build();
    }

    public Review getReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id가 없습니다."));
        return review;
    }

    public List<ReviewReadResponse> findAllByHospitalId(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()-> new IllegalArgumentException("해당 id가 없습니다."));
        List<ReviewReadResponse> reviews = reviewRepository.findByHospital(hospital)
                .stream().map(review -> ReviewReadResponse.builder()
                        .id(review.getId())
                        .title(review.getTitle())
                        .content(review.getContent())
                        .patientName(review.getPatientName())
                        .hospitalName(review.getHospital().getHospitalName())
                        .build()
                ).collect(Collectors.toList());
        return reviews;
    }
}