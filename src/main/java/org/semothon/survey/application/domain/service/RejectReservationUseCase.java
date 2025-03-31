package org.semothon.survey.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.application.domain.repository.ApplicationRepository;
import org.semothon.survey.application.exception.ApplicationErrorType;
import org.semothon.survey.application.exception.ApplicationException;
import org.semothon.survey.application.presentation.request.RejectRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RejectReservationUseCase {

    private final ApplicationRepository applicationRepository;

    @Transactional
    public void execute(RejectRequest rejectRequest) {
        Application application = applicationRepository.findByApplicationId(rejectRequest.applicationId())
                .orElseThrow(()-> new ApplicationException(ApplicationErrorType.NOT_EXIST_AVAILABLE_APPLICATION));

        application.reject(rejectRequest.applicationRejectReason());
        applicationRepository.save(application);
    }
}
