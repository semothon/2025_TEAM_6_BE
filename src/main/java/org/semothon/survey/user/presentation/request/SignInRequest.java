package org.semothon.survey.user.presentation.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.semothon.survey.user.domain.entity.UserRole;

public record SignInRequest (
        @NotBlank(message = "사용자 아이디는 공백일 수 없습니다.")
        String userId,

        @NotBlank(message = "사용자 비밀번호는 공백일 수 없습니다.")
        String userPassword,

        @Enumerated(EnumType.STRING)
        @NotNull(message = "사용자 역할은 필수입니다.")
        UserRole userRole
){ }
