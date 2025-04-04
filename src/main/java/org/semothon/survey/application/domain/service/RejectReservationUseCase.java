package org.semothon.survey.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.application.domain.repository.ApplicationRepository;
import org.semothon.survey.application.exception.ApplicationErrorType;
import org.semothon.survey.application.exception.ApplicationException;
import org.semothon.survey.application.presentation.request.ApplicationRejectRequest;
import org.semothon.survey.availability.domain.entity.Availability;
import org.semothon.survey.availability.domain.repository.AvailabilityRepository;
import org.semothon.survey.availability.exception.AvailabilityErrorType;
import org.semothon.survey.availability.exception.AvailabilityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RejectReservationUseCase {

    private final ApplicationRepository applicationRepository;
    private final AvailabilityRepository availabilityRepository;

    @Transactional
    public void execute(ApplicationRejectRequest applicationRejectRequest) {
        Application application = applicationRepository.findByApplicationId(applicationRejectRequest.applicationId())
                .orElseThrow(()-> new ApplicationException(ApplicationErrorType.NOT_EXIST_AVAILABLE_APPLICATION));

        Availability availability = availabilityRepository.findById(application.getAvailabilityId())
                .orElseThrow(()-> new AvailabilityException(AvailabilityErrorType.NOT_EXIST_AVAILABLE_APPLICATION));

        application.reject(applicationRejectRequest.applicationRejectReason());
        availability.reject();
    }
}
