package org.semothon.survey.report.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.semothon.survey.report.exception.ReportErrorType;
import org.semothon.survey.report.exception.ReportException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_report")
@Data
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private Long applicationId; //FK(Application.applicationId)

    @Enumerated(EnumType.STRING)
    private ApplicationStatus reportStatus;

    private String reportRejectReason;
    private LocalDate reportSubmittedAt;
    private LocalDate reportUpdatedAt;

    private String reportUrl;
    private String reportBeforeImage;
    private String reportAfterImage;

    public void approve() {
        if (this.reportStatus != ApplicationStatus.PENDING) {
            throw new ReportException(ReportErrorType.CANNOT_APPROVE_STATUS);
        }
        this.reportStatus = ApplicationStatus.APPROVED;
        this.reportUpdatedAt = LocalDate.now();
    }

    public void reject(String reportRejectReason) {
        if (this.reportStatus != ApplicationStatus.PENDING) {
            throw new ReportException(ReportErrorType.CANNOT_REJECT_STATUS);
        }
        this.reportStatus = ApplicationStatus.REJECTED;
        this.reportRejectReason = reportRejectReason;
        this.reportUpdatedAt = LocalDate.now();
    }
}
