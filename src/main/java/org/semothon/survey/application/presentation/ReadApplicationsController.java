package org.semothon.survey.application.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.application.domain.service.ReadApplicationsUseCase;
import org.semothon.survey.application.presentation.response.ReadApplicationsResponse;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReadApplicationsController {

    private final ReadApplicationsUseCase readApplicationsUseCase;
    /**
     * 쿼리 파라미터 userId와 status에 따라 신청서를 조회합니다.
     * - userId가 제공되지 않으면, status에 따라 전체 또는 상태별 조회
     * - userId가 제공되면, status에 따라 해당 사용자 전체 또는 상태별 조회
     */
    @GetMapping
    public ResponseEntity<List<ReadApplicationsResponse>> getApplications(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "status", required = false) ApplicationStatus status) {

        List<ReadApplicationsResponse> responses;

        if (userId != null && !userId.isBlank()) {
            responses = (status != null)
                    ? readApplicationsUseCase.execute(userId, status)
                    : readApplicationsUseCase.execute(userId);
        } else {
            responses = (status != null)
                    ? readApplicationsUseCase.execute(status)
                    : readApplicationsUseCase.execute();
        }

        return ResponseEntity.ok(responses);
    }
}
