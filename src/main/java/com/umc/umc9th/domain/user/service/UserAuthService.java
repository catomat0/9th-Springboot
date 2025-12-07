package com.umc.umc9th.domain.user.service;

import com.umc.umc9th.domain.user.dto.request.SignUpRequest;
import com.umc.umc9th.domain.user.dto.response.AuthResponse;
import com.umc.umc9th.domain.user.entity.Role;
import com.umc.umc9th.domain.user.entity.User;
import com.umc.umc9th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse signUp(SignUpRequest request) {
        // 이메일 중복 확인
        if (userRepository.existsByUserEmail(request.userEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        // 닉네임 중복 확인
        if (userRepository.existsByUserNickname(request.userNickname())) {
            throw new RuntimeException("이미 존재하는 닉네임입니다.");
        }

        // User 엔티티 생성
        User user = User.builder()
                .userName(request.userName())
                .password(passwordEncoder.encode(request.password()))
                .userEmail(request.userEmail())
                .userNickname(request.userNickname())
                .userPhone(request.userPhone())
                .userGender(request.userGender())
                .userBirth(request.userBirth())
                .role(Role.ROLE_USER)
                .userPoint(0L)
                .isActive(true)
                .isSocial(false)
                .build();

        User savedUser = userRepository.save(user);

        return new AuthResponse(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getUserEmail(),
                savedUser.getUserNickname(),
                savedUser.getRole(),
                "회원가입이 완료되었습니다."
        );
    }
}
