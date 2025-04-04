package org.semothon.survey.classroom.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.classroom.domain.entity.Building;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.semothon.survey.classroom.domain.repository.ClassRoomRepository;
import org.semothon.survey.classroom.presentation.response.RoomListResponse;
import org.semothon.survey.classroom.presentation.response.RoomPreviewInfo;
import org.semothon.survey.core.enumerate.ApplicationStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadRoomListUseCase {

    private final ClassRoomRepository classRoomRepository;

    public RoomListResponse execute(Building classroomBuilding) {
        // 해당 빌딩에 해당하는 모든 ClassRoom을 조회
        List<ClassRoom> classRooms = classRoomRepository.findAllByClassroomBuilding(classroomBuilding);

        // 각 ClassRoom 객체에서 필요한 정보를 RoomPreviewInfo로 변환
        List<RoomPreviewInfo> roomPreviewInfos = classRooms.stream()
                .map(classRoom -> new RoomPreviewInfo(
                        classRoom.getClassroomId(),
                        classRoom.getClassroomNumber(),
                        classRoom.getClassroomCapacity(),
                        classRoom.getClassroomImages().getFirst()
                ))
                .collect(Collectors.toList());

        // count는 classRooms의 개수
        Integer count = classRooms.size();

        return RoomListResponse.of(count, roomPreviewInfos);
    }

    public List<ClassRoom> execute(LocalDate date, LocalTime startTime, LocalTime endTime) {
        return classRoomRepository.findAvailableClassrooms(
                date,
                startTime,
                endTime,
                List.of(ApplicationStatus.PENDING, ApplicationStatus.APPROVED)
        );
    }
}