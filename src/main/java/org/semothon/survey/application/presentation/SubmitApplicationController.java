package org.semothon.survey.application.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.service.SubmitApplicationUseCase;
import org.semothon.survey.application.presentation.request.ApplicationSubmitRequest;
import org.semothon.survey.core.support.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubmitApplicationController {

    private final SubmitApplicationUseCase submitApplicationUseCase;

    @PostMapping("/api/application/submit")
    public ApiResponse<?> submitApplication(@RequestBody ApplicationSubmitRequest request) {
        submitApplicationUseCase.submitApplication(request);
        return ApiResponse.success();
    }
}
