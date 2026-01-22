package com.example.Spring_Project.controler;

import com.example.Spring_Project.entity.EmployeeJobEntity;
import com.example.Spring_Project.service.EmployeeJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee-job")
@RequiredArgsConstructor
public class EmployeeJobController {
    private final EmployeeJobService employeeJobService;

    @GetMapping("/search")
    public Page<EmployeeJobEntity> search(
            @PageableDefault(size = 20, direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return employeeJobService.getAll(pageable);
    }

}
