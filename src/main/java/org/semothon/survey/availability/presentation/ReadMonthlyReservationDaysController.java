package org.semothon.survey.availability.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.availability.domain.service.ReadMonthlyReservedDaysUseCase;
import org.semothon.survey.core.support.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReadMonthlyReservationDaysController {

    private final ReadMonthlyReservedDaysUseCase readMonthlyReservedDaysUseCase;
    /**
     * 특정 강의실의 지정 연도, 월에 예약이 있는 날짜 목록을 반환.
     *
     * 예: GET /api/classrooms/101/reserved-days?year=2025&month=4
     */
    @GetMapping("/api/classrooms/{classroomId}/reserved-days")
    public ApiResponse<List<LocalDate>> getReservedDays(
            @PathVariable Long classroomId,
            @RequestParam int year,
            @RequestParam int month) {

        List<LocalDate> reservedDays = readMonthlyReservedDaysUseCase.execute(classroomId, year, month);
        return ApiResponse.success(reservedDays);
    }
}
