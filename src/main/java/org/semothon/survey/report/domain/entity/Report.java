package org.semothon.survey.report.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semothon.survey.core.enums.ApplicationStatus;

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
    private LocalDateTime reportSubmittedAt;
}
