package org.semothon.survey.report.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semothon.survey.application.exception.ApplicationErrorType;
import org.semothon.survey.application.exception.ApplicationException;
import org.semothon.survey.core.enumerate.ApplicationStatus;

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
            throw new ApplicationException(ApplicationErrorType.CANNOT_APPROVE_STATUS);
        }
        this.reportStatus = ApplicationStatus.APPROVED;
        this.reportUpdatedAt = LocalDate.now();
    }

    public void reject(String applicationRejectReason) {
        if (this.reportStatus != ApplicationStatus.PENDING) {
            throw new ApplicationException(ApplicationErrorType.CANNOT_REJECT_STATUS);
        }
        this.reportStatus = ApplicationStatus.REJECTED;
        this.reportRejectReason = applicationRejectReason;
        this.reportUpdatedAt = LocalDate.now();
    }
}
