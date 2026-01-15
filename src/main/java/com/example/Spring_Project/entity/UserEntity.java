package com.example.Spring_Project.entity;

import com.example.Spring_Project.common.AuditField;
import com.example.Spring_Project.model.Enum.ERole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity extends AuditField implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(unique=true, nullable=false)
    private String email;
    @Column(nullable=false)
    private String password;
    @Enumerated(EnumType.STRING)
    private ERole role;
    private boolean enabled = true;

}
