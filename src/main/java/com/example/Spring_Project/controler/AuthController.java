package com.example.Spring_Project.controler;

import com.example.Spring_Project.model.Dto.TokenResponse;
import com.example.Spring_Project.service.AuthService;
import com.example.Spring_Project.model.Dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        authService.register(user);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody User user) {
        return authService.login(user);
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestBody String refreshToken) {
        return authService.refreshToken(refreshToken);
    }

}
