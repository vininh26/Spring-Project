package com.example.Spring_Project.repository;

import com.example.Spring_Project.entity.RefreshTokenEntity;
import com.example.Spring_Project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);
    Optional<RefreshTokenEntity> findByUserEntity(UserEntity userEntity);
    void deleteByUserEntity(UserEntity userEntity);
}