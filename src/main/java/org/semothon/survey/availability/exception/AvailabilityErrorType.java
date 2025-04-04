package org.semothon.survey.availability.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.semothon.survey.core.support.error.ErrorType;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AvailabilityErrorType implements ErrorType {
    NOT_EXIST_AVAILABLE_APPLICATION(HttpStatus.BAD_REQUEST, "해당 조회대상이 존재하지 않습니다."),
    CANNOT_APPROVE_STATUS(HttpStatus.BAD_REQUEST, "조회대상을 승인할 수 없는 상태입니다."),
    CANNOT_REJECT_STATUS(HttpStatus.BAD_REQUEST, "조회대상을 거절할 수 없는 상태입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}

