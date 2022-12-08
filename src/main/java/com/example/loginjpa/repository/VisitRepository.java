package com.example.loginjpa.repository;

import com.example.loginjpa.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit,Long> {
}
