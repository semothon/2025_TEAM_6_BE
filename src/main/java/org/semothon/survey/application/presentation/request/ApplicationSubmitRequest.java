package org.semothon.survey.application.presentation.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record ApplicationSubmitRequest(

        String userId,

        Long classroomId,

        LocalDate applicationUseDate,

        LocalTime applicationStart,

        LocalTime applicationEnd,

        String applicationPurpose,

        Integer applicationParticipants,

        String applicationUrl
) { }
