package org.semothon.survey.report.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.report.domain.entity.Report;
import org.semothon.survey.report.domain.repository.ReportRepository;
import org.semothon.survey.report.exception.ReportErrorType;
import org.semothon.survey.report.exception.ReportException;
import org.semothon.survey.report.presentation.request.ReportRejectRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RejectReportUseCase {

    private final ReportRepository reportRepository;

    @Transactional
    public void execute(ReportRejectRequest reportRejectRequest) {
        Report report = reportRepository.findByApplicationId(reportRejectRequest.reportId())
                .orElseThrow(()-> new ReportException(ReportErrorType.NOT_EXIST_AVAILABLE_APPLICATION));

        report.reject(reportRejectRequest.reportRejectReason());
        reportRepository.save(report);
    }
}
