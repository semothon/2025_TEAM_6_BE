package org.semothon.survey.report.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.application.domain.repository.ApplicationRepository;
import org.semothon.survey.application.exception.ApplicationErrorType;
import org.semothon.survey.application.exception.ApplicationException;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.semothon.survey.classroom.domain.repository.ClassRoomRepository;
import org.semothon.survey.classroom.exception.ClassRoomErrorType;
import org.semothon.survey.classroom.exception.ClassRoomException;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.semothon.survey.report.domain.entity.Report;
import org.semothon.survey.report.domain.repository.ReportRepository;
import org.semothon.survey.report.exception.ReportErrorType;
import org.semothon.survey.report.exception.ReportException;
import org.semothon.survey.report.presentation.response.ReadReportsResponse;
import org.semothon.survey.report.presentation.response.ReportDetailResponse;
import org.semothon.survey.user.domain.entity.User;
import org.semothon.survey.user.domain.repository.UserRepository;
import org.semothon.survey.user.exception.UserErrorType;
import org.semothon.survey.user.exception.UserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadReportUseCase {

    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final ClassRoomRepository classroomRepository;
    private final ApplicationRepository applicationRepository;

    // 전체 결과보고서 조회
    public List<ReadReportsResponse> execute() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream()
                .map(report -> {
                    Application application = applicationRepository.findByApplicationId(report.getApplicationId())
                            .orElseThrow(() -> new ApplicationException(ApplicationErrorType.NOT_EXIST_AVAILABLE_APPLICATION));
                    ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                            .orElseThrow(() -> new ClassRoomException(ClassRoomErrorType.NOT_EXIST_AVAILABLE_CLASSROOM));
                    return ReadReportsResponse.from(report, application, classroom);
                })
                .toList();
    }

    // 상태별 결과보고서 조회
    public List<ReadReportsResponse> execute(ApplicationStatus status) {
        List<Report> reports = reportRepository.findAllByReportStatus(status);
        return reports.stream()
                .map(report -> {
                    Application application = applicationRepository.findByApplicationId(report.getApplicationId())
                            .orElseThrow(() -> new ReportException(ReportErrorType.NOT_EXIST_AVAILABLE_REPORT));
                    ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                            .orElseThrow(() -> new ClassRoomException(ClassRoomErrorType.NOT_EXIST_AVAILABLE_CLASSROOM));
                    return ReadReportsResponse.from(report, application, classroom);
                })
                .toList();
    }

    // 사용자(userId)에 해당하는 결과보고서 전체 조회
    public List<ReadReportsResponse> execute(String userId) {
        List<Report> reports = reportRepository.findAll();
        return reports.stream()
                .map(report -> {
                    Application application = applicationRepository.findByApplicationId(report.getApplicationId())
                            .orElseThrow(() -> new ReportException(ReportErrorType.NOT_EXIST_AVAILABLE_REPORT));
                    if (!application.getUserId().equals(userId)) {
                        return null;
                    }
                    ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                            .orElseThrow(() -> new ClassRoomException(ClassRoomErrorType.NOT_EXIST_AVAILABLE_CLASSROOM));
                    return ReadReportsResponse.from(report, application, classroom);
                })
                .filter(Objects::nonNull)
                .toList();
    }

    // 사용자(userId)와 상태에 해당하는 결과보고서 조회
    public List<ReadReportsResponse> execute(String userId, ApplicationStatus status) {
        List<Report> reports = reportRepository.findAllByReportStatus(status);
        return reports.stream()
                .map(report -> {
                    Application application = applicationRepository.findByApplicationId(report.getApplicationId())
                            .orElseThrow(() -> new ReportException(ReportErrorType.NOT_EXIST_AVAILABLE_REPORT));
                    if (!application.getUserId().equals(userId)) {
                        return null;
                    }
                    ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                            .orElseThrow(() -> new ClassRoomException(ClassRoomErrorType.NOT_EXIST_AVAILABLE_CLASSROOM));
                    return ReadReportsResponse.from(report, application, classroom);
                })
                .filter(Objects::nonNull)
                .toList();
    }

    // 결과보고서 상세조회
    public ReportDetailResponse execute(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ReportException(ReportErrorType.NOT_EXIST_AVAILABLE_REPORT));

        Application application = applicationRepository.findByApplicationId(report.getApplicationId())
                .orElseThrow(() -> new ReportException(ReportErrorType.NOT_EXIST_AVAILABLE_REPORT));

        ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                .orElseThrow(() -> new ClassRoomException(ClassRoomErrorType.NOT_EXIST_AVAILABLE_CLASSROOM));

        User user = userRepository.findById(application.getUserId())
                .orElseThrow(() -> new UserException(UserErrorType.USER_NOT_FOUND));

        return ReportDetailResponse.from(report, application, classroom, user);
    }
}
