package org.semothon.survey.report.presentation.request;

import jakarta.validation.constraints.NotEmpty;

public record SubmitReportRequest(

        @NotEmpty(message = "applicationId이 결과보고서 요청값에 없습니다.")
        Long applicationId,

        @NotEmpty(message = "reportUrl이 결과보고서 요청값에 없습니다.")
        String reportUrl
) { }
