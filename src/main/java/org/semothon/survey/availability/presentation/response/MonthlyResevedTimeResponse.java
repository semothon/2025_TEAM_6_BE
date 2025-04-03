package org.semothon.survey.availability.presentation.response;

import org.semothon.survey.availability.domain.entity.Availability;
import org.semothon.survey.core.enumerate.ApplicationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record MonthlyResevedTimeResponse (

        LocalDate date,

        LocalTime start,

        LocalTime end,

        ApplicationStatus reservationStatus,

        String availabilityResponsibility
) {
    public static MonthlyResevedTimeResponse from(Availability availability) {
        return new MonthlyResevedTimeResponse(
                availability.getAvailabilityDate(),
                availability.getAvailabilityStart(),
                availability.getAvailabilityEnd(),
                availability.getReservationStatus(),
                availability.getUserId()
        );
    }
}