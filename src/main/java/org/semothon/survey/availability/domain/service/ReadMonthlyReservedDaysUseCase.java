package org.semothon.survey.availability.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.availability.domain.entity.Availability;
import org.semothon.survey.availability.domain.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadMonthlyReservedDaysUseCase {
    private final AvailabilityRepository availabilityRepository;

    /**
     * 특정 강의실의 지정 연도, 월에 예약이 있는 날짜를 조회합니다.
     *
     * @param classroomId 강의실 ID
     * @param year 연도 (예: 2025)
     * @param month 월 (예: 4)
     * @return 예약이 있는 날짜 리스트 (정렬된 상태)
     */
    public List<LocalDate> execute(Long classroomId, int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();

        List<Availability> availabilities =
                availabilityRepository.findAllByClassroomIdAndAvailabilityDateBetween(classroomId, start, end);

        return availabilities.stream()
                .map(Availability::getAvailabilityDate)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
