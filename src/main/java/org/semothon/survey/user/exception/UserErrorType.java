package org.semothon.survey.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.semothon.survey.core.support.error.ErrorType;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorType implements ErrorType {
    NOT_EXIST_AVAILABLE_USER(HttpStatus.BAD_REQUEST, "해당 유저가 존재하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
