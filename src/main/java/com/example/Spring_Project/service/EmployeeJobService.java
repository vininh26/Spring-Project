package com.example.Spring_Project.service;

import com.example.Spring_Project.entity.EmployeeJobEntity;
import com.example.Spring_Project.repository.EmployeeJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeJobService {
    private final EmployeeJobRepository employeeJobRepository;

    public Page<EmployeeJobEntity> getAll( Pageable pageable) {
        return employeeJobRepository.findAll(pageable);
    }

}
