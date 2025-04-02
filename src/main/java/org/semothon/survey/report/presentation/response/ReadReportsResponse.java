package org.semothon.survey.report.presentation.response;

import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.semothon.survey.report.domain.entity.Report;

import java.time.LocalDate;

public record ReadReportsResponse(

        Long reportId,

        Long applicaitonId, // 신청서 아이디

        String reportUrl, // 신청서 url

        LocalDate reportSubmittedAt, // 신청날짜

        String classroom, // 신청 강의실

        String semester
) {
    public static ReadReportsResponse from(Report report, Application application, ClassRoom classroom) {
        // 강의실 정보: 건물 설명과 강의실 번호 결합
        String classroomInfo = classroom.getClassroomBuilding().getDescription() + " " + classroom.getClassroomNumber();
        // 학기는 reportSubmittedAt 날짜를 기준으로 추론 (혹은 필요시 application의 날짜 사용)
        String semester = resolveSemester(report.getReportSubmittedAt());
        return new ReadReportsResponse(
                report.getReportId(),
                report.getApplicationId(),
                report.getReportUrl(),
                report.getReportSubmittedAt(),
                classroomInfo,
                semester
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
