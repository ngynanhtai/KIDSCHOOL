package com.example.backend.repository;

import com.example.backend.model.SuggestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SuggestionRepository extends JpaRepository<SuggestionEntity, Long> {
}
