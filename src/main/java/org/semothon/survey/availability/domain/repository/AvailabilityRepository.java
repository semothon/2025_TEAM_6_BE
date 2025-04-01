package org.semothon.survey.availability.domain.repository;

import org.semothon.survey.availability.domain.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByClassroomIdAfterAndAvailabilityDate(Long classroomId, LocalDate date);
    // 특정 강의실의 지정 기간(한 달) 전체 예약 기록 조회
    List<Availability> findAllByClassroomIdAndAvailabilityDateBetween(Long classroomId, LocalDate startDate, LocalDate endDate);

}
