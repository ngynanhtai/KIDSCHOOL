package com.example.backend.repository;

import com.example.backend.model.TimeTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends JpaRepository<TimeTableEntity, Long> {
}
