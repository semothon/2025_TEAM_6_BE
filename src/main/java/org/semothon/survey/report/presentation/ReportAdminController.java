package org.semothon.survey.report.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.service.ApproveReservationUseCase;
import org.semothon.survey.application.domain.service.RejectReservationUseCase;
import org.semothon.survey.application.presentation.request.ApplicationRejectRequest;
import org.semothon.survey.core.support.response.ApiResponse;
import org.semothon.survey.report.domain.service.ApproveReportUseCase;
import org.semothon.survey.report.domain.service.RejectReportUseCase;
import org.semothon.survey.report.presentation.request.ReportRejectRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportAdminController {

    private final RejectReportUseCase rejectReportUseCase;
    private final ApproveReportUseCase approveReportUseCase;

    @PostMapping("api/report/approve")
    public ApiResponse<?> appoveApplication(@RequestParam Long reportId) {
        approveReportUseCase.execute(reportId);
        return ApiResponse.success();
    }

    @PostMapping("api/report/reject")
    public ApiResponse<?> rejectApplication(@RequestBody ReportRejectRequest reportRejectRequest) {
        rejectReportUseCase.execute(reportRejectRequest);
        return ApiResponse.success();
    }
}
