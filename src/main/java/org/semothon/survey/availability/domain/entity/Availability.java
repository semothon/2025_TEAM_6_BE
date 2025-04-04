package org.semothon.survey.availability.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.semothon.survey.application.exception.ApplicationErrorType;
import org.semothon.survey.application.exception.ApplicationException;
import org.semothon.survey.core.enumerate.ApplicationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_availability")
@Data
@NoArgsConstructor
@SuperBuilder
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availabilityId;
    private Long classroomId;  //FK(Classroom.classroomId)
    private String userId;

    private LocalDate availabilityDate;
    private LocalTime availabilityStart;
    private LocalTime availabilityEnd;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ApplicationStatus reservationStatus = ApplicationStatus.PENDING;

    public static Availability create(Long classroomId, LocalDate date, LocalTime start,
                                                       LocalTime end, String reserveUser) {
        return Availability.builder()
                .classroomId(classroomId)
                .userId(reserveUser)
                .availabilityDate(date)
                .availabilityStart(start)
                .availabilityEnd(end)
                .build();
    }

    public void approve() {
        if (this.reservationStatus != ApplicationStatus.PENDING) {
            throw new ApplicationException(ApplicationErrorType.CANNOT_APPROVE_STATUS);
        }
        this.reservationStatus = ApplicationStatus.APPROVED;
    }

    public void reject(String applicationRejectReason) {
        if (this.reservationStatus != ApplicationStatus.PENDING) {
            throw new ApplicationException(ApplicationErrorType.CANNOT_REJECT_STATUS);
        }
        this.reservationStatus = ApplicationStatus.REJECTED;
    }
}
