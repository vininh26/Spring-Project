package com.example.Spring_Project.service;

import com.example.Spring_Project.entity.RefreshTokenEntity;
import com.example.Spring_Project.entity.UserEntity;
import com.example.Spring_Project.model.Dto.TokenResponse;
import com.example.Spring_Project.model.Dto.User;
import com.example.Spring_Project.model.Enum.ERole;
import com.example.Spring_Project.repository.RefreshTokenRepository;
import com.example.Spring_Project.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;
    private final RefreshTokenService refreshTokenService;
    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final Duration LOCK_DURATION = Duration.ofMinutes(15);

    @Transactional
    public void register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setEmail(user.getEmail());
        if (user.getRole() != null) {
            userEntity.setRole(ERole.valueOf(user.getRole().name()));
        }else {
            userEntity.setRole(ERole.ROLE_USER);
        }

        userRepository.save(userEntity);
    }

    @Transactional
    public TokenResponse login(User user) {
        UserEntity userEntity = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Wrong credentials");
        }
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userEntity.getUsername());

        String accessToken = jwtUtils.generateToken(userDetails);
        RefreshTokenEntity refreshToken = refreshTokenService.create(userEntity);

        return new TokenResponse(accessToken,refreshToken.getToken());
    }

    public TokenResponse refreshToken(String refreshToken) {
        RefreshTokenEntity rt = refreshTokenService.verify(refreshToken);
        UserDetails ud =
                userDetailsService.loadUserByUsername(rt.getUserEntity().getUsername());
        return new TokenResponse(jwtUtils.generateToken(ud),rt.getToken());
    }
}
