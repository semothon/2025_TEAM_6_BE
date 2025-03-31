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
    // 쿼리 파라미터 status가 있으면 해당 상태, 없으면 전체 조회
    @GetMapping("/api/application")
    public ResponseEntity<List<ReadApplicationsResponse>> getApplications(
            @RequestParam(value = "status", required = false) ApplicationStatus status) {
        List<ReadApplicationsResponse> responses = (status == null)
                ? readApplicationsUseCase.execute()
                : readApplicationsUseCase.execute(status);
        return ResponseEntity.ok(responses);
    }
}
