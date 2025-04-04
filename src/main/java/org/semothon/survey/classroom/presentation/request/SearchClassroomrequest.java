package org.semothon.survey.classroom.presentation.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record SearchClassroomrequest(
        @Schema(description = "예약 날짜 (YYYY-MM-DD)", example = "2025-04-04")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date,

        @Schema(description = "시작 시간 (HH:mm:ss)", example = "09:00:00")
        @DateTimeFormat(pattern = "HH:mm:ss")
        LocalTime startTime,

        @Schema(description = "종료 시간 (HH:mm:ss)", example = "11:00:00")
        @DateTimeFormat(pattern = "HH:mm:ss")
        LocalTime endTime
) {
}
