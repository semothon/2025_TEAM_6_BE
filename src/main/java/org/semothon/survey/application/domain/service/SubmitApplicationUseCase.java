package org.semothon.survey.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.application.domain.repository.ApplicationRepository;
import org.semothon.survey.application.presentation.request.ApplicationSubmitRequest;
import org.semothon.survey.availability.domain.entity.Availability;
import org.semothon.survey.availability.domain.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmitApplicationUseCase {

    private final ApplicationRepository applicationRepository;
    private final AvailabilityRepository availabilityRepository;

    public Application submitApplication(ApplicationSubmitRequest request) {
        // Application 생성 및 저장
        Application application = Application.create(request);
        Application savedApplication = applicationRepository.save(application);

        // Availability 생성 (신청 정보와 동일한 사용 날짜, 시간, 강의실, userId 정보 사용)
        Availability availability = Availability.create(
                request.classroomId(),
                request.applicationUseDate(),
                request.applicationStart(),
                request.applicationEnd(),
                request.userId()   // 예약 신청한 사용자 ID
        );
        availabilityRepository.save(availability);
        savedApplication.setApplicationId(availability.getAvailabilityId());

        return savedApplication;
    }
}
