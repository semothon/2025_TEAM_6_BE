package org.semothon.survey.application.presentation.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ApplicationSubmitRequest(

        @NotEmpty(message = "userId이 대여신청 요청값에 없습니다.")
        String userId,

        @NotNull(message = "classroomId이 대여신청 요청값에 없습니다.")
        Long classroomId,

        @NotNull(message = "applicationUseDate이 대여신청 요청값에 없습니다.")
        LocalDate applicationUseDate,

        @NotNull(message = "applicationStart이 대여신청 요청값에 없습니다.")
        LocalTime applicationStart,

        @NotNull(message = "applicationEnd이 대여신청 요청값에 없습니다.")
        LocalTime applicationEnd,

        @NotEmpty(message = "applicationPurpose이 대여신청 요청값에 없습니다.")
        String applicationPurpose,

        @NotNull(message = "applicationParticipants이 대여신청 요청값에 없습니다.")
        Integer applicationParticipants,

        @NotEmpty(message = "applicationUrl이 대여신청 요청값에 없습니다.")
        String applicationUrl
) { }
