package org.semothon.survey.report.presentation.request;

public record SubmitReportRequest(
        Long applicationId,
        String reportUrl
) { }
