package org.semothon.survey.document.domain.enumerate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentType {
    APPLICATION_FORM("사용 신청서 양식"),
    REPORT_FORM("결과 보고서 양식");

    private final String description;
}