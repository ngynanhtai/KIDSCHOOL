package com.example.backend.repository;

import com.example.backend.model.TypeClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeClassRepository extends JpaRepository<TypeClassEntity, Long> {
}
