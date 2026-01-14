package com.example.Spring_Project.repository;

import com.example.Spring_Project.entity.EmployeeJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJobRepository extends JpaRepository<EmployeeJobEntity, Long> {
}
