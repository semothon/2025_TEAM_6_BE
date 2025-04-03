package org.semothon.survey.report.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.report.domain.entity.Report;
import org.semothon.survey.report.domain.repository.ReportRepository;
import org.semothon.survey.report.presentation.request.SubmitReportRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmitReportUseCase {

    private final ReportRepository reportRepository;

    /**
     * 사용자가 제출한 보고서 정보를 받아 Report 엔티티를 생성하고 저장합니다.
     *
     * @param request SubmitReportRequest: applicationId와 reportUrl 포함
     * @return 저장된 Report 엔티티
     */
    public Report submitReport(SubmitReportRequest request) {
        Report report = Report.create(request.applicationId(), request.reportUrl());
        return reportRepository.save(report);
    }
}
