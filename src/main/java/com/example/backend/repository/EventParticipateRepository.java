package com.example.backend.repository;

import com.example.backend.model.EventParticipateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParticipateRepository extends JpaRepository<EventParticipateEntity, Long> {
}
