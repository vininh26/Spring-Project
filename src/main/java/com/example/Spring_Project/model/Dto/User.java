package com.example.Spring_Project.model.Dto;

import com.example.Spring_Project.model.Enum.ERole;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean enabled = true;
    private ERole role;
}
