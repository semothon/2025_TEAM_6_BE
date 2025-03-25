package org.semothon.survey.availability.domain.repository;

import org.semothon.survey.availability.domain.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByRoomIdAndAvailabilityDate(Long roomId, LocalDate date);
}
