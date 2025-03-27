package org.semothon.survey.classroom.presentation.response;

public record RoomPreviewInfo(

        Long classroomId,

        Integer classroomNumber,

        Integer classroomCapacity
) { }
