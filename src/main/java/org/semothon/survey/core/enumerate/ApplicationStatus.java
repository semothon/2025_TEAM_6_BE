package org.semothon.survey.core.enumerate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplicationStatus {
    PENDING("승인 대기중"),
    APPROVED("승인 완료"),
    REJECTED("반려"),
    COMPLETED("완료");

    private final String description;
}
