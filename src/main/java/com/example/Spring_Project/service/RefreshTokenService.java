package com.example.Spring_Project.service;

import com.example.Spring_Project.entity.RefreshTokenEntity;
import com.example.Spring_Project.entity.UserEntity;
import com.example.Spring_Project.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository repo;

    @Transactional
    public RefreshTokenEntity create(UserEntity user) {
        return repo.findByUserEntity(user)
                .map(rt -> {
                    rt.setToken(UUID.randomUUID().toString());
                    rt.setExpiryDate(Instant.now().plus(30, ChronoUnit.DAYS));
                    return rt; // UPDATE
                })
                .orElseGet(() -> {
                    RefreshTokenEntity rt = new RefreshTokenEntity();
                    rt.setUserEntity(user);
                    rt.setToken(UUID.randomUUID().toString());
                    rt.setExpiryDate(Instant.now().plus(30, ChronoUnit.DAYS));
                    return repo.save(rt); // INSERT
                });
    }

    @Transactional
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
