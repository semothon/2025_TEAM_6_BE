package org.semothon.survey.application.presentation.response;

import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.classroom.domain.entity.ClassRoom;

import java.time.LocalDate;

public record ReadApplicationsResponse(

        Long applicaitonId, // 신청서 아이디

        LocalDate applicationDate, // 신청날짜

        String classroom, // 신청 강의실

        String semester
) {
    public static ReadApplicationsResponse from(Application application, ClassRoom classRoom) {
        return new ReadApplicationsResponse(
                application.getApplicationId(),
                application.getApplicationDate(),
                classRoom.getClassroomBuilding() + " " + classRoom.getClassroomNumber(),
                resolveSemester(application.getApplicationDate())
        );
    }

    private static String resolveSemester(LocalDate date) {
        int month = date.getMonthValue();
        int year = date.getYear();

        if (month >= 3 && month <= 6) return year + "년 1학기";
        if (month >= 7 && month <= 8) return year + "년 여름학기";
        if (month >= 9 && month <= 12) return year + "년 2학기";
        return year + "년 겨울학기"; // 1~2월
    }
}
