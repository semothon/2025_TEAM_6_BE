package org.semothon.survey.report.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.semothon.survey.report.exception.ReportErrorType;
import org.semothon.survey.report.exception.ReportException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_report")
@Data
@NoArgsConstructor
@SuperBuilder
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private Long applicationId; //FK(Application.applicationId)

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ApplicationStatus reportStatus = ApplicationStatus.PENDING;;
    private String reportRejectReason;

    @Builder.Default
    private LocalDate reportSubmittedAt  = LocalDate.now();
    private LocalDate reportUpdatedAt;

    private String reportUrl;

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

    public static Report create(Long applicationId, String reportUrl) {
        return Report.builder()
                .applicationId(applicationId)
                .reportUrl(reportUrl)
                // reportStatus와 reportSubmittedAt은 @Builder.Default로 자동 적용됨
                .build();
    }
}
