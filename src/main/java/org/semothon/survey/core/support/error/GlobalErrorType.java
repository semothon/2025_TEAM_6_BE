package org.semothon.survey.core.support.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorType implements ErrorType {

    E500(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 내부 오류입니다."),
    S3_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "S3 업로드에 실패했습니다."),
    ;

    private final HttpStatus status;

    private final String message;
}