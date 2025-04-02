package org.semothon.survey.classroom.presentation.response;

public record RoomDetailResponse (

    RoomPreviewInfo roomPreviewInfos, // 수용인원, 강의실번호

    String classroomBuilding, // 대학관

    String classroomCautions // 사용 시 주의 사항
){}
