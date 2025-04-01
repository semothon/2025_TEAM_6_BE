package org.semothon.survey.availability.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.availability.domain.service.ReadMonthlyReservedTimeUseCase;
import org.semothon.survey.availability.presentation.response.MonthlyResevedTimeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReadMonthlyReservationController {

    private final ReadMonthlyReservedTimeUseCase readMonthlyReservedTimeUseCase;

    /**
     * 예:
     * 1. GET /api/classrooms/{classroomId}/availability?year=2025&month=4
     *    -> 해당 강의실의 2025년 4월 전체 예약 기록 조회
     *
     * 2. GET /api/classrooms/{classroomId}/availability?year=2025&month=4&day=15
     *    -> 해당 강의실의 2025년 4월 15일 예약 기록 조회
     *
     * reservationStatus는 입력받지 않으므로 전체 예약 기록을 반환합니다.
     */
    @GetMapping("/api/classrooms/{classroomId}/availability")
    public ResponseEntity<List<MonthlyResevedTimeResponse>> getAvailability(
            @PathVariable Long classroomId,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam(value = "day", required = false) Integer day) {

        // reservationStatus는 입력받지 않으므로 null 전달 (전체 예약 기록 조회)
        List<MonthlyResevedTimeResponse> responses = readMonthlyReservedTimeUseCase.execute(classroomId, year, month, day);
        return ResponseEntity.ok(responses);
    }

}
