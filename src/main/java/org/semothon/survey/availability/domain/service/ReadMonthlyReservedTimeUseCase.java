package org.semothon.survey.availability.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.availability.domain.entity.Availability;
import org.semothon.survey.availability.domain.repository.AvailabilityRepository;
import org.semothon.survey.availability.presentation.response.MonthlyResevedTimeResponse;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadMonthlyReservedTimeUseCase {
    private final AvailabilityRepository availabilityRepository;

    /**
     * 특정 강의실의 지정 연도와 월에 대한 예약 기록을 조회합니다.
     * 만약 reservationStatus가 null이 아니면 해당 상태의 예약만 조회합니다.
     */
    public List<MonthlyResevedTimeResponse> execute(Long classroomId, int year, int month, Integer day) {
        LocalDate start;
        LocalDate end;
        if (day != null) {
            // day가 제공되면 해당 날짜만 조회
            start = LocalDate.of(year, month, day);
            end = start;
        } else {
            // day가 없으면 해당 월의 첫날부터 말일까지 조회
            YearMonth ym = YearMonth.of(year, month);
            start = ym.atDay(1);
            end = ym.atEndOfMonth();
        }

        List<Availability> availabilities = availabilityRepository.findAllByClassroomIdAndAvailabilityDateBetween(
                classroomId, start, end);
        return availabilities.stream()
                .map(MonthlyResevedTimeResponse::from)
                .collect(Collectors.toList());
    }
}
