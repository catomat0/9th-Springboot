package com.umc.umc9th.domain.user.dto.request;

import com.umc.umc9th.domain.user.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record SignUpRequest(
        @NotBlank(message = "사용자 이름은 필수입니다.")
        String userName,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
                message = "비밀번호는 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다.")
        String password,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String userEmail,

        @NotBlank(message = "닉네임은 필수입니다.")
        String userNickname,

        @NotBlank(message = "전화번호는 필수입니다.")
        @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$",
                message = "올바른 전화번호 형식이 아닙니다.")
        String userPhone,

        @NotNull(message = "성별은 필수입니다.")
        Gender userGender,

        LocalDateTime userBirth
) {
}