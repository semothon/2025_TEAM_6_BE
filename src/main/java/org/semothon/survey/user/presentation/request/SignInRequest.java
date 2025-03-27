package org.semothon.survey.user.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record SignInRequest (
        @NotBlank(message = "사용자 아이디는 공백일 수 없습니다.")
        String username,

        @NotBlank(message = "사용자 비밀번호는 공백일 수 없습니다.")
        String userPassword,

        @NotBlank(message = "사용자 역할군은 공백일 수 없습니다.")
        String userRole
){ }
