package org.semothon.survey.report.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.semothon.survey.core.support.error.ErrorType;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReportErrorType implements ErrorType {
    NOT_EXIST_AVAILABLE_REPORT(HttpStatus.BAD_REQUEST, "해당 결과보고서가 존재하지 않습니다."),
    CANNOT_APPROVE_STATUS(HttpStatus.BAD_REQUEST, "승인할 수 없는 상태입니다."),
    CANNOT_REJECT_STATUS(HttpStatus.BAD_REQUEST, "거절할 수 없는 상태입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}

