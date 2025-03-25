package org.semothon.survey.report.domain.repository;

import org.semothon.survey.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByApplicationId(Long applicationId);
}