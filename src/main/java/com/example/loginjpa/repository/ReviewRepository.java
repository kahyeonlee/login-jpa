package com.example.loginjpa.repository;

import com.example.loginjpa.domain.Hospital;
import com.example.loginjpa.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHospital(Hospital hospital);
}