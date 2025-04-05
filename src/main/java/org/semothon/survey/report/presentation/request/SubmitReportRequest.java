package org.semothon.survey.report.presentation.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SubmitReportRequest(

        @NotNull(message = "applicationId이 결과보고서 요청값에 없습니다.")
        Long applicationId,

        @NotEmpty(message = "reportUrl이 결과보고서 요청값에 없습니다.")
        String reportUrl
) { }
