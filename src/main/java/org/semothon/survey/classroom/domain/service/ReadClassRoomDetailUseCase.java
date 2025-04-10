package org.semothon.survey.classroom.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.classroom.domain.entity.ClassRoom;
import org.semothon.survey.classroom.domain.repository.ClassRoomRepository;
import org.semothon.survey.classroom.exception.ClassRoomErrorType;
import org.semothon.survey.classroom.exception.ClassRoomException;
import org.semothon.survey.classroom.presentation.response.RoomDetailResponse;
import org.semothon.survey.classroom.presentation.response.RoomPreviewInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadClassRoomDetailUseCase {
    private final ClassRoomRepository classRoomRepository;

    public RoomDetailResponse execute(Long classroomId) {
        ClassRoom classroom = classRoomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassRoomException(ClassRoomErrorType.NOT_EXIST_AVAILABLE_CLASSROOM));

        RoomPreviewInfo previewInfo = RoomPreviewInfo.from(classroom);

        return new RoomDetailResponse(
                previewInfo,
                classroom.getClassroomBuilding().getDescription(),
                classroom.getClassroomCautions()
        );
    }
}
