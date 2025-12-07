package com.umc.umc9th.domain.auth.jwt.service;

import com.umc.umc9th.domain.auth.jwt.JWTProvider;
import com.umc.umc9th.domain.auth.jwt.dto.JWTResponseDTO;
import com.umc.umc9th.domain.auth.jwt.dto.RefreshRequestDTO;
import com.umc.umc9th.domain.auth.jwt.entity.RefreshEntity;
import com.umc.umc9th.domain.auth.jwt.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final RefreshRepository refreshRepository;

    // Refresh 토큰으로 Access 토큰 재발급 로직 (Rotate 포함)
    @Transactional
    public JWTResponseDTO refreshRotate(RefreshRequestDTO dto) {

        String refreshToken = dto.getRefreshToken();

        // Refresh 토큰 검증
        Boolean isValid = JWTProvider.isValid(refreshToken, false);
        if (!isValid) {
            throw new RuntimeException("유효하지 않은 refreshToken입니다.");
        }

        // 정보 추출
        String userEmail = JWTProvider.getUserEmail(refreshToken);
        String role = JWTProvider.getRole(refreshToken);

        // 토큰 생성
        String newAccessToken = JWTProvider.createJWT(userEmail, role, true);
        String newRefreshToken = JWTProvider.createJWT(userEmail, role, false);

        // 기존 Refresh 토큰 DB 삭제 후 신규 추가
        RefreshEntity newRefreshEntity = RefreshEntity.builder()
                .userEmail(userEmail)
                .refresh(newRefreshToken)
                .build();

        removeRefresh(refreshToken);
        refreshRepository.save(newRefreshEntity);

        return new JWTResponseDTO(newAccessToken, newRefreshToken);
    }

    // JWT Refresh 토큰 발급 후 저장 메소드
    @Transactional
    public void addRefresh(String userEmail, String refreshToken) {

        RefreshEntity entity = RefreshEntity.builder()
                .userEmail(userEmail)
                .refresh(refreshToken)
                .build();

        refreshRepository.save(entity);
    }

    // JWT Refresh 존재 확인 메소드
    @Transactional(readOnly = true)
    public Boolean existsRefresh(String refreshToken) {
        return refreshRepository.existsByRefresh(refreshToken);
    }

    // JWT Refresh 토큰 삭제 메소드
    @Transactional
    public void removeRefresh(String refreshToken) {
        refreshRepository.deleteByRefresh(refreshToken);
    }

    // 특정 유저 Refresh 토큰 모두 삭제 (탈퇴)
    @Transactional
    public void removeRefreshUser(String userEmail) {
        refreshRepository.deleteByUserEmail(userEmail);
    }
}
