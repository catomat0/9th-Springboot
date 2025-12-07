package com.umc.umc9th.domain.user.dto.response;

import com.umc.umc9th.domain.user.entity.Role;

public record AuthResponse(
        Long userId,
        String userName,
        String userEmail,
        String userNickname,
        Role role,
        String message
) {
}