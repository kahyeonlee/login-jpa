package com.example.loginjpa.domain.dto;

import com.example.loginjpa.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewReadResponse {
    private Long id;
    private String title;
    private String content;
    private String patientName;
    private String hospitalName;

    public static ReviewReadResponse fromEntity(Review review) {
        ReviewReadResponse response = ReviewReadResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .patientName(review.getPatientName())
                .hospitalName(review.getHospital().getHospitalName()) //매핑된 결과
                .build();
        return response;
    }
}