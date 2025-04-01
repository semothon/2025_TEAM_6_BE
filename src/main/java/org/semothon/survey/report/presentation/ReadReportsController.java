package org.semothon.survey.report.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.semothon.survey.report.domain.service.ReadReportUseCase;
import org.semothon.survey.report.presentation.response.ReadReportsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReadReportsController {

    private final ReadReportUseCase readReportUseCase;
    // 쿼리 파라미터 status가 있으면 해당 상태, 없으면 전체 조회
    @GetMapping("/api/report")
    public ResponseEntity<List<ReadReportsResponse>> getApplications(
            @RequestParam(value = "status", required = false) ApplicationStatus status) {
        List<ReadReportsResponse> responses = (status == null)
                ? readReportUseCase.execute()
                : readReportUseCase.execute(status);
        return ResponseEntity.ok(responses);
    }
}
