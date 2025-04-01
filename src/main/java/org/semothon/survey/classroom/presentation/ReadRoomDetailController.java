package org.semothon.survey.classroom.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.classroom.domain.service.ReadClassRoomDetailUseCase;
import org.semothon.survey.classroom.presentation.response.RoomDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReadRoomDetailController {

    private final ReadClassRoomDetailUseCase readClassRoomDetailUseCase;

    @GetMapping("/api/classroom/detail")
    public ResponseEntity<RoomDetailResponse> getClassRoomDetail(@RequestParam("classroomId") Long classroomId) {
        RoomDetailResponse response = readClassRoomDetailUseCase.execute(classroomId);
        return ResponseEntity.ok(response);
    }
}
