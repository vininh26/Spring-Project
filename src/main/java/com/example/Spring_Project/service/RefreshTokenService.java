package com.example.Spring_Project.service;

import com.example.Spring_Project.entity.RefreshTokenEntity;
import com.example.Spring_Project.entity.UserEntity;
import com.example.Spring_Project.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository repo;

    public RefreshTokenService(RefreshTokenRepository repo) {
        this.repo = repo;
    }

    public RefreshTokenEntity create(UserEntity user) {
        repo.deleteByUserEntity(user);

        RefreshTokenEntity rt = new RefreshTokenEntity();
        rt.setUserEntity(user);
        rt.setToken(UUID.randomUUID().toString());
        rt.setExpiryDate(Instant.now().plus(30, ChronoUnit.DAYS));

        return repo.save(rt);
    }

    public RefreshTokenEntity verify(String token) {
        RefreshTokenEntity rt = repo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (rt.getExpiryDate().isBefore(Instant.now())) {
            repo.delete(rt);
            throw new RuntimeException("Refresh token expired");
        }
        return rt;
    }
}
