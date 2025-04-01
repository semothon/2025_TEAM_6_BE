package org.semothon.survey.application.domain.service;

import com.amazonaws.services.kms.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.entity.Application;
import org.semothon.survey.application.domain.repository.ApplicationRepository;
import org.semothon.survey.application.presentation.response.ReadApplicationsResponse;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.semothon.survey.classroom.domain.repository.ClassRoomRepository;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadApplicationsUseCase {

    private final ApplicationRepository applicationRepository;
    private final ClassRoomRepository classroomRepository;

    // 전체 신청서 조회
    public List<ReadApplicationsResponse> execute() {
        List<Application> applications = applicationRepository.findAll();
        return applications.stream()
                .map(application -> {
                    ClassRoom classroom = classroomRepository.findById(application.getClassroomId())
                            .orElseThrow(() -> new RuntimeException("Classroom not found for id: " + application.getClassroomId()));
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
                            .orElseThrow(() -> new RuntimeException("Classroom not found for id: " + application.getClassroomId()));
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
                            .orElseThrow(() -> new RuntimeException("Classroom not found for id: " + application.getClassroomId()));
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
                            .orElseThrow(() -> new RuntimeException("Classroom not found for id: " + application.getClassroomId()));
                    return ReadApplicationsResponse.from(application, classroom);
                })
                .toList();
    }
}
