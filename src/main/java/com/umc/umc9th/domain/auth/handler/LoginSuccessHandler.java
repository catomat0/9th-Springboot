package com.umc.umc9th.domain.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.umc9th.domain.auth.jwt.JWTProvider;
import com.umc.umc9th.domain.auth.jwt.dto.JWTResponseDTO;
import com.umc.umc9th.domain.auth.jwt.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
            Authentication authentication) throws IOException, ServletException {

        String userEmail = authentication.getName();
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // JWT 토큰 생성
        String accessToken = JWTProvider.createJWT(userEmail, role, true);
        String refreshToken = JWTProvider.createJWT(userEmail, role, false);

        // Refresh 토큰 DB 저장
        jwtService.addRefresh(userEmail, refreshToken);

        // 응답
        JWTResponseDTO jwtResponse = new JWTResponseDTO(accessToken, refreshToken);
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(jwtResponse));
    }
}
