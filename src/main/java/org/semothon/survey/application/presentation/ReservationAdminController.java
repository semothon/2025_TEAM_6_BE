package org.semothon.survey.application.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.service.ApproveReservationUseCase;
import org.semothon.survey.application.domain.service.RejectReservationUseCase;
import org.semothon.survey.application.presentation.request.ApplicationRejectRequest;
import org.semothon.survey.core.support.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationAdminController {

    private final RejectReservationUseCase rejectReservationUseCase;
    private final ApproveReservationUseCase approveReservationUseCase;

    @PostMapping("api/application/approve")
    public ApiResponse<?> appoveApplication(@RequestParam Long applicationId) {
        approveReservationUseCase.execute(applicationId);
        return ApiResponse.success();
    }

    @PostMapping("api/application/reject")
    public ApiResponse<?> rejectApplication(@RequestBody ApplicationRejectRequest applicationRejectRequest) {
        rejectReservationUseCase.execute(applicationRejectRequest);
        return ApiResponse.success();
    }
}
