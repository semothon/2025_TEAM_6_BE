package org.semothon.survey.report.presentation.request;

public record ReportRejectRequest (

    Long reportId,

    String reportRejectReason
){}
