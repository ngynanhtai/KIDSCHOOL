package com.example.backend.repository;

import com.example.backend.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    @Query(value = "SELECT s FROM StudentEntity s WHERE s.accountId = ?1")
    StudentEntity findByAccountId(String accountid);
}
