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

    /**
     * 쿼리 파라미터 userId와 status에 따라 결과보고서를 조회합니다.
     * - userId가 제공되지 않으면 기존대로 전체 또는 상태별 조회
     * - userId가 제공되면 해당 사용자의 보고서만 조회하고, status가 있다면 상태 필터링을 추가합니다.
     */
    @GetMapping("/api/report")
    public ResponseEntity<List<ReadReportsResponse>> getReports(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "status", required = false) ApplicationStatus status) {

        List<ReadReportsResponse> responses;
        if (userId != null && !userId.isBlank()) {
            responses = (status != null)
                    ? readReportUseCase.execute(userId, status)
                    : readReportUseCase.execute(userId);
        } else {
            responses = (status != null)
                    ? readReportUseCase.execute(status)
                    : readReportUseCase.execute();
        }
        return ResponseEntity.ok(responses);
    }
}
