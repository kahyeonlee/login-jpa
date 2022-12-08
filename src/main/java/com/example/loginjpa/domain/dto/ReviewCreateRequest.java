package com.example.loginjpa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class ReviewCreateRequest {
    private Long hospitalId;
    private String userName;
    private String title;
    private String content;
}
