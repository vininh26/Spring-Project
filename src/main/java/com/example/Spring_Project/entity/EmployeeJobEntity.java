package com.example.Spring_Project.entity;

import com.example.Spring_Project.common.AuditField;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "employee_jobs")
@Data
@NoArgsConstructor
public class EmployeeJobEntity extends AuditField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    private String name;
    @Column(unique=true, nullable=false)
    private String email;
    private String phone;
    private String address;
    private String company;
    private String text;
    private String description;
    private String jobTitle;
}
