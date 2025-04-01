package org.semothon.survey.application.domain.service;

import com.amazonaws.services.kms.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.application.domain.repository.ApplicationRepository;
import org.semothon.survey.application.exception.ApplicationErrorType;
import org.semothon.survey.application.exception.ApplicationException;
import org.semothon.survey.application.presentation.response.ApplicationDetailResponse;
import org.semothon.survey.application.presentation.response.ReadApplicationsResponse;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.semothon.survey.classroom.domain.repository.ClassRoomRepository;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.semothon.survey.user.domain.entity.User;
import org.semothon.survey.user.domain.repository.UserRepository;
import org.semothon.survey.user.exception.UserErrorType;
import org.semothon.survey.user.exception.UserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadApplicationsUseCase {

    private final ApplicationRepository applicationRepository;
    private final ClassRoomRepository classroomRepository;
    private final UserRepository userRepository;

    // 전체 신청서 조회
    public List<ReadApplicationsResponse> execute() {
        List<Application> applications = applicationRepository.findAll();
        return applications.stream()
                .map(application -> {
                    ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                            .orElseThrow(() -> new ApplicationException(ApplicationErrorType.NOT_EXIST_AVAILABLE_APPLICATION));
                    return ReadApplicationsResponse.from(application, classroom);
                })
                .toList();
    }

    // 상태별 신청서 조회
    public List<ReadApplicationsResponse> execute(ApplicationStatus status) {
        List<Application> applications = applicationRepository.findAllByApplicationStatus(status);
        return applications.stream()
                .map(application -> {
                    ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                            .orElseThrow(() -> new ApplicationException(ApplicationErrorType.NOT_EXIST_AVAILABLE_APPLICATION));
                    return ReadApplicationsResponse.from(application, classroom);
                })
                .toList();
    }

    // userId에 해당하는 신청서 전체 조회
    public List<ReadApplicationsResponse> execute(String userId) {
        List<Application> applications = applicationRepository.findAllByUserId(userId);
        return applications.stream()
                .map(application -> {
                    ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                            .orElseThrow(() -> new ApplicationException(ApplicationErrorType.NOT_EXIST_AVAILABLE_APPLICATION));
                    return ReadApplicationsResponse.from(application, classroom);
                })
                .toList();
    }

    // userId와 상태에 해당하는 신청서 조회
    public List<ReadApplicationsResponse> execute(String userId, ApplicationStatus status) {
        List<Application> applications = applicationRepository.findAllByUserIdAndApplicationStatus(userId, status);
        return applications.stream()
                .map(application -> {
                    ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                            .orElseThrow(() -> new ApplicationException(ApplicationErrorType.NOT_EXIST_AVAILABLE_APPLICATION));
                    return ReadApplicationsResponse.from(application, classroom);
                })
                .toList();
    }

    public ApplicationDetailResponse execute(Long applicationId) {
        Application application = applicationRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> new ApplicationException(ApplicationErrorType.NOT_EXIST_AVAILABLE_APPLICATION));

        User user = userRepository.findById(application.getUserId())
                .orElseThrow(() -> new UserException(UserErrorType.USER_NOT_FOUND));

        ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                .orElseThrow(() -> new RuntimeException("Classroom not found for id: " + application.getApplicationId()));

        return ApplicationDetailResponse.from(application, user, classroom);
    }
}
