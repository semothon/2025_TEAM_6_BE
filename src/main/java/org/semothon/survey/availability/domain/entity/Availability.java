package org.semothon.survey.availability.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semothon.survey.core.enumerate.ApplicationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_availability")
@Data
@NoArgsConstructor
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availabilityId;

    private Long classroomId;  //FK(Classroom.classroomId)

    private LocalDate availabilityDate;
    private LocalTime availabilityStart;
    private LocalTime availabilityEnd;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus reservationStatus;
    private String availabilityResponsibility; // 등록자 (관리자 userId)

}
