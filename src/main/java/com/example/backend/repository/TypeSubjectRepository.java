package com.example.backend.repository;

import com.example.backend.model.TypeSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSubjectRepository extends JpaRepository<TypeSubjectEntity, Long> {
}
