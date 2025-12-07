package com.umc.umc9th.domain.auth.jwt.dto;

public record RefreshRequestDTO(
        String refreshToken
) {
    public String getRefreshToken() {
        return refreshToken;
    }
}
