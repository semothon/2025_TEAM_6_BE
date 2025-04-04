package org.semothon.survey.classroom.presentation.response;

import org.semothon.survey.classroom.domain.entity.ClassRoom;

public record RoomPreviewInfo(

        Long classroomId,

        Integer classroomNumber,

        Integer classroomCapacity,

        String classroomImage
) {
    public static RoomPreviewInfo from(ClassRoom classroom) {
        return new RoomPreviewInfo(
                classroom.getClassroomId(),
                classroom.getClassroomNumber(),
                classroom.getClassroomCapacity(),
                classroom.getClassroomImages().getFirst()
        );
    }
}
