package com.umc.umc9th.domain.auth.handler;

import com.umc.umc9th.domain.auth.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenLogoutHandler implements LogoutHandler {

    private final JwtService jwtService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        
        String refreshToken = request.getHeader("RefreshToken");
        
        if (refreshToken != null && jwtService.existsRefresh(refreshToken)) {
            jwtService.removeRefresh(refreshToken);
        }
    }
}
