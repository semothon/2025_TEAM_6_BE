package org.semothon.survey.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.semothon.survey.core.support.error.ErrorType;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorType implements ErrorType {
    NOT_EXIST_AVAILABLE_USER(HttpStatus.BAD_REQUEST, "해당 유저가 존재하지 않습니다."),
    DUPLICATE_USER_ID(HttpStatus.BAD_REQUEST, "이미 사용 중인 아이디입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 아이디입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
