package org.semothon.survey.report.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.core.support.response.ApiResponse;
import org.semothon.survey.report.domain.entity.Report;
import org.semothon.survey.report.domain.service.SubmitReportUseCase;
import org.semothon.survey.report.presentation.request.SubmitReportRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubmitReportController {

    private final SubmitReportUseCase submitReportUseCase;

    @PostMapping("/api/report")
    public ApiResponse<?> submitReport(@RequestBody SubmitReportRequest request) {
        submitReportUseCase.submitReport(request);
        return ApiResponse.success();
    }
}
