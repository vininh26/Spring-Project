package com.example.Spring_Project.repository;

import com.example.Spring_Project.entity.RefreshTokenEntity;
import com.example.Spring_Project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);
    void deleteByUserEntity(UserEntity userEntity);
}