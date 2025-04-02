package org.semothon.survey.application.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semothon.survey.application.exception.ApplicationErrorType;
import org.semothon.survey.application.exception.ApplicationException;
import org.semothon.survey.core.enumerate.ApplicationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_application")
@Data
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    private String userId;  //FK(User.userId)
    private Long classroomId;    //FK(Classroom.classroomId)

    private String applicationPurpose;
    private Integer applicationParticipants;

    private LocalDate applicationUseDate;
    private LocalTime applicationStart;
    private LocalTime applicationEnd;
    private LocalDate applicationDate;

    private LocalDate applicationApprovedAt;
    private String applicationApprover;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private String applicationRejectReason;
    private String applicationUrl;

    public void approve() {
        if (this.applicationStatus != ApplicationStatus.PENDING) {
            throw new ApplicationException(ApplicationErrorType.CANNOT_APPROVE_STATUS);
        }
        this.applicationStatus = ApplicationStatus.APPROVED;
        this.applicationApprovedAt = LocalDate.now();
    }

    public void reject(String applicationRejectReason) {
        if (this.applicationStatus != ApplicationStatus.PENDING) {
            throw new ApplicationException(ApplicationErrorType.CANNOT_REJECT_STATUS);
        }
        this.applicationStatus = ApplicationStatus.REJECTED;
        this.applicationRejectReason = applicationRejectReason;
        this.applicationApprovedAt = LocalDate.now();
    }
}
