package org.semothon.survey.application.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.semothon.survey.application.exception.ApplicationErrorType;
import org.semothon.survey.application.exception.ApplicationException;
import org.semothon.survey.application.presentation.request.ApplicationSubmitRequest;
import org.semothon.survey.availability.domain.entity.Availability;
import org.semothon.survey.core.enumerate.ApplicationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_application")
@Data
@NoArgsConstructor
@SuperBuilder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    private String userId;  //FK(User.userId)
    private Long classroomId;    //FK(Classroom.classroomId)
    private Long availabilityId;

    private String applicationPurpose;
    private Integer applicationParticipants;

    private LocalDate applicationUseDate;
    private LocalTime applicationStart;
    private LocalTime applicationEnd;

    @Builder.Default
    private LocalDate applicationDate = LocalDate.now();

    private LocalDate applicationApprovedAt;
    private String applicationApprover;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus =ApplicationStatus.PENDING;

    private String applicationRejectReason;
    private String applicationUrl;

    public static Application create(ApplicationSubmitRequest request) {
        return Application.builder()
                .userId(request.userId())
                .classroomId(request.classroomId())
                .applicationUseDate(request.applicationUseDate())
                .applicationStart(request.applicationStart())
                .applicationEnd(request.applicationEnd())
                .applicationPurpose(request.applicationPurpose())
                .applicationParticipants(request.applicationParticipants())
                .applicationUrl(request.applicationUrl())
                .build();
    }

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
