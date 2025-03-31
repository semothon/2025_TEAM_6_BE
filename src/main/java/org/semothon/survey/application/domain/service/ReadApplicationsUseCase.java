package org.semothon.survey.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.application.domain.repository.ApplicationRepository;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadApplicationsUseCase {

    private final ApplicationRepository applicationRepository;

    // 전체 신청서 조회
    public List<Application> execute() {
        return applicationRepository.findAll();
    }

    // 특정 상태의 신청서 조회
    public List<Application> execute(ApplicationStatus status) {
        return applicationRepository.findAllByApplicationStatus(status);
    }
}
