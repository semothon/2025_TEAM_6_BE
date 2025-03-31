package org.semothon.survey.classroom.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.classroom.domain.entity.Building;
import org.semothon.survey.classroom.domain.service.ReadRoomListUseCase;
import org.semothon.survey.classroom.presentation.response.RoomListResponse;
import org.semothon.survey.core.support.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReadRoomListController {

    private final ReadRoomListUseCase readRoomListUseCase;

    @GetMapping("api/classroom")
    public ApiResponse<RoomListResponse> signIn(@RequestParam Building classroomBuilding) {
        RoomListResponse roomListResponse = readRoomListUseCase.execute(classroomBuilding);
        return ApiResponse.success(roomListResponse);
    }
}
