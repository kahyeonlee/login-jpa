package com.example.loginjpa.controller;

import com.example.loginjpa.domain.Review;
import com.example.loginjpa.domain.dto.ReviewCreateRequest;
import com.example.loginjpa.domain.dto.ReviewCreateResponse;
import com.example.loginjpa.domain.dto.ReviewReadResponse;
import com.example.loginjpa.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reviews")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
//    @PostMapping
//    public ResponseEntity<String> writeReview(Authentication authentication) {
//        return ResponseEntity.ok()
//                .body(reviewService.createReview(authentication.getName()));
//    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewCreateResponse> get(@PathVariable Long id, @RequestBody ReviewCreateRequest reviewCreateRequest) {
        log.info("{}", reviewCreateRequest);
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewReadResponse> get(@PathVariable Long id) {
        Review review = reviewService.getReview(id);
        ReviewReadResponse response = ReviewReadResponse.fromEntity(review);
        return ResponseEntity.ok().body(response);
    }



}
