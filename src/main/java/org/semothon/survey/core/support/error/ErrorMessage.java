package org.semothon.survey.core.support.error;

import lombok.Getter;

@Getter
public class ErrorMessage {

    private final String code;

    private final String message;

    public ErrorMessage(ErrorType errorType) {
        this.code = errorType.name();
        this.message = errorType.getMessage();
    }

}