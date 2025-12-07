package com.umc.umc9th.domain.auth.service;

import com.umc.umc9th.domain.auth.jwt.JWTProvider;
import com.umc.umc9th.domain.auth.jwt.dto.JWTResponseDTO;
import com.umc.umc9th.domain.auth.jwt.service.JwtService;
import com.umc.umc9th.domain.user.dto.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public JWTResponseDTO login(LoginRequest request) {
        // 인증 시도
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(
                request.userEmail(), 
                request.password()
            );

        Authentication authentication = authenticationManager.authenticate(authToken);

        // 인증 성공 - JWT 토큰 생성
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

        return new JWTResponseDTO(accessToken, refreshToken);
    }
}
