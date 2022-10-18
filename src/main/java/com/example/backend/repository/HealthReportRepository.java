package com.example.backend.repository;

import com.example.backend.model.HealthReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthReportRepository extends JpaRepository<HealthReportEntity, Long> {
}
