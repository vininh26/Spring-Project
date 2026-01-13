package com.example.Spring_Project.model.Dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
//    private Date expiry;
}
