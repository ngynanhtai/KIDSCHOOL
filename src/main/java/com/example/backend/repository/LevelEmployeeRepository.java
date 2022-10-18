package com.example.backend.repository;

import com.example.backend.model.LevelEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelEmployeeRepository extends JpaRepository<LevelEmployeeEntity, Long> {
}
