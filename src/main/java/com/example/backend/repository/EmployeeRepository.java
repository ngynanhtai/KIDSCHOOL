package com.example.backend.repository;

import com.example.backend.model.EmployeeEntity;
import com.example.backend.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query(value = "SELECT s FROM EmployeeEntity s WHERE s.accountID = ?1")
    EmployeeEntity findByAccountId(String accountId);
}
