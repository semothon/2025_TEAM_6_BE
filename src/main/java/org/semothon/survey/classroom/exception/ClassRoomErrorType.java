package org.semothon.survey.classroom.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.semothon.survey.core.support.error.ErrorType;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClassRoomErrorType implements ErrorType {
    NOT_EXIST_AVAILABLE_CLASSROOM(HttpStatus.BAD_REQUEST, "해당 강의실이 존재하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}

