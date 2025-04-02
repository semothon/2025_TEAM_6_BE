package org.semothon.survey.application.presentation.response;

import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.semothon.survey.user.domain.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;

public record ApplicationDetailResponse(

        String userId,              // 사용자 학번

        String userName,            // 사용자 이름

        String userNumber,          // 사용자 휴대폰 번호

        String classroom,           // 신청 강의실 (건물 설명 + 강의실 번호)

        String applicationPurpose,  // 사용 목적

        LocalDate applicationUseDate,   // 사용 날짜

        LocalTime applicationStart,     // 사용 시작시간

        LocalTime applicationEnd,       // 사용 끝 시간

        LocalDate applicationDate,      // 접수 일자

        ApplicationStatus applicationStatus,  // 신청 상태

        String applicationRejectReason, // 반려 사유

        String applicationUrl           // 파일 위치
) {

    public static ApplicationDetailResponse from(Application application, User user, ClassRoom classroom) {
        // 예시: 강의실 정보는 건물 설명과 강의실 번호를 결합하여 생성합니다.
        String classroomInfo = classroom.getClassroomBuilding().getDescription() + " " + classroom.getClassroomNumber();
        return new ApplicationDetailResponse(
                application.getUserId(),
                user.getUserName(),
                user.getUserNumber(),
                classroomInfo,
                application.getApplicationPurpose(),
                application.getApplicationUseDate(),
                application.getApplicationStart(),
                application.getApplicationEnd(),
                application.getApplicationDate(),
                application.getApplicationStatus(),
                application.getApplicationRejectReason(),
                application.getApplicationUrl()
        );
    }
}
