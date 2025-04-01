package org.semothon.survey.report.presentation.response;

import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.semothon.survey.report.domain.entity.Report;
import org.semothon.survey.user.domain.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReportDetailResponse(

        String userId,              // 사용자 학번

        String userName,            // 사용자 이름

        String userNumber,          // 사용자 휴대폰 번호

        String classroom,           // 신청 강의실 (건물 설명 + 강의실 번호)

        String applicationPurpose,  // 사용 목적

        LocalDate applicationUseDate,   // 사용 날짜

        LocalTime applicationStart,     // 사용 시작시간

        LocalTime applicationEnd,       // 사용 끝 시간

        LocalDate reportSubmittedAt,      // 접수 일자 (보고서 제출일)

        ApplicationStatus reportStatus,  // 신청 상태

        String reportRejectReason, // 반려 사유

        String reportUrl           // 파일 위치
) {
    /**
     * 도메인 객체(Report, Application, ClassRoom, User)를 받아 DTO를 생성합니다.
     */
    public static ReportDetailResponse from(Report report, Application application, ClassRoom classroom, User user) {
        String classroomInfo = classroom.getClassroomBuilding().getDescription() + " " + classroom.getClassroomNumber();
        return new ReportDetailResponse(
                user.getUserId(),
                user.getUserName(),
                user.getUserNumber(),
                classroomInfo,
                application.getApplicationPurpose(),
                application.getApplicationUseDate(),
                application.getApplicationStart(),
                application.getApplicationEnd(),
                report.getReportSubmittedAt(),
                report.getReportStatus(),
                report.getReportRejectReason(),
                report.getReportUrl()
        );
    }
}