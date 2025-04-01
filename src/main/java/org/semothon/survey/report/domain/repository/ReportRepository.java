package org.semothon.survey.report.domain.repository;

import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.semothon.survey.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportId(Long reportId);
    List<Report> findAllByReportStatus(ApplicationStatus reportStatus);
}