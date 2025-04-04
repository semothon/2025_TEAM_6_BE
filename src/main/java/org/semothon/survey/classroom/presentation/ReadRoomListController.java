package org.semothon.survey.classroom.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.semothon.survey.classroom.domain.entity.Building;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.semothon.survey.classroom.domain.service.ReadRoomListUseCase;
import org.semothon.survey.classroom.presentation.request.SearchClassroomrequest;
import org.semothon.survey.classroom.presentation.response.RoomListResponse;
import org.semothon.survey.classroom.presentation.response.RoomPreviewInfo;
import org.semothon.survey.core.support.response.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReadRoomListController {

    private final ReadRoomListUseCase readRoomListUseCase;

    @GetMapping("api/classroom")
    public ApiResponse<RoomListResponse> signIn(@RequestParam Building classroomBuilding) {
        RoomListResponse roomListResponse = readRoomListUseCase.execute(classroomBuilding);
        return ApiResponse.success(roomListResponse);
    }

    @Operation(summary = "특정 날짜와 시간 범위에 가능한 강의실 조회")
    @GetMapping("api/classroom/available")
    public ResponseEntity<RoomListResponse> getAvailableClassrooms(@ModelAttribute SearchClassroomrequest request) {
        List<ClassRoom> classrooms = readRoomListUseCase.execute(request.date(), request.startTime(), request.endTime());
        List<RoomPreviewInfo> roomPreviewInfos = classrooms.stream()
                .map(RoomPreviewInfo::from)
                .collect(Collectors.toList());
        RoomListResponse response = RoomListResponse.of(roomPreviewInfos.size(), roomPreviewInfos);
        return ResponseEntity.ok(response);
    }
}
