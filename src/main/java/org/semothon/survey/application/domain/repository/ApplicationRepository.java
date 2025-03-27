package org.semothon.survey.application.domain.repository;

import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByApplicationStatus(ApplicationStatus status);
    List<Application> findByUserId(String userId);
}
