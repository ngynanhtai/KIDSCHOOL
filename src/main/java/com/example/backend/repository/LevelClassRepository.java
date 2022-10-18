package com.example.backend.repository;

import com.example.backend.model.LevelClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelClassRepository extends JpaRepository<LevelClassEntity, Long> {
}
