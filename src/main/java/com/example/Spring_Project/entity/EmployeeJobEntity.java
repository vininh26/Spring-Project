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
public class EmployeeJobEntity extends AuditField implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "company")
    private String company;
    @Column(name = "text")
    private String text;
    @Column(name = "description")
    private String description;
    @Column(name = "jobTitle")
    private String jobTitle;
}
