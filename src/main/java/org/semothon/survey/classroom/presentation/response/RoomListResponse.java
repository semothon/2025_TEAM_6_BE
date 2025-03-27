package org.semothon.survey.classroom.presentation.response;

import java.util.List;

public record RoomListResponse (

        Integer count,

        List<RoomPreviewInfo> roomPreviewInfos
) {
    public static RoomListResponse of(Integer count, List<RoomPreviewInfo> roomPreviewInfos) {
        return new RoomListResponse(count, roomPreviewInfos);
    }
}
