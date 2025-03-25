package org.semothon.survey.application.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semothon.survey.core.enums.ApplicationStatus;

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
    private Long roomId;    //FK(Classroom.classroomId)

    private String applicationPurpose;
    private Integer applicationParticipants;

    private LocalDate applicationUseDate;
    private LocalTime applicationStart;
    private LocalTime applicationEnd;
    private LocalDate applicationDate;

    private String applicationDesker;
    private LocalDate applicationApprovedAt;
    private String applicationApprover;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private String applicationRejectReason;
}
