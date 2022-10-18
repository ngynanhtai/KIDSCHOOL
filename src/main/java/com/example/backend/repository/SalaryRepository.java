package com.example.backend.repository;

import com.example.backend.model.EmployeeEntity;
import com.example.backend.model.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryEntity, Long> {

    @Query(value = "SELECT s FROM SalaryEntity s WHERE s.month = ?1")
     List<SalaryEntity> findAllSalariesByMonth(int month);
}
