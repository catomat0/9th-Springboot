package com.umc.umc9th.domain.auth.controller;

import com.umc.umc9th.domain.auth.jwt.dto.JWTResponseDTO;
import com.umc.umc9th.domain.auth.jwt.dto.RefreshRequestDTO;
import com.umc.umc9th.domain.auth.jwt.service.JwtService;
import com.umc.umc9th.domain.auth.service.LoginService;
import com.umc.umc9th.domain.user.dto.request.LoginRequest;
import com.umc.umc9th.domain.user.dto.request.SignUpRequest;
import com.umc.umc9th.domain.user.dto.response.AuthResponse;
import com.umc.umc9th.domain.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증 API", description = "회원가입, 로그인, 토큰 관리 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final LoginService loginService;
    private final JwtService jwtService;

    @Operation(
            summary = "회원가입",
            description = "새로운 사용자를 등록합니다. 이메일은 중복될 수 없습니다."
    )
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        AuthResponse response = authService.signUp(request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "로그인",
            description = "이메일과 비밀번호로 로그인하여 JWT 토큰을 발급받습니다."
    )
    @PostMapping("/login")
    public ResponseEntity<JWTResponseDTO> login(@Valid @RequestBody LoginRequest request) {
        JWTResponseDTO response = loginService.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "액세스 토큰 갱신",
            description = "리프레시 토큰을 사용하여 새로운 액세스 토큰과 리프레시 토큰을 발급받습니다. (Refresh Token Rotation)"
    )
    @PostMapping("/refresh")
    public ResponseEntity<JWTResponseDTO> refresh(@RequestBody RefreshRequestDTO request) {
        JWTResponseDTO response = jwtService.refreshRotate(request);
        return ResponseEntity.ok(response);
    }
}
