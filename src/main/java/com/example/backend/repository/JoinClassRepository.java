package com.example.backend.repository;

import com.example.backend.model.JoinClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinClassRepository extends JpaRepository<JoinClassEntity, Long> {
}
