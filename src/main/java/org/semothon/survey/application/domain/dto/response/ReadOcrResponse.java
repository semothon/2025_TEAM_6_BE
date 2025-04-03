package org.semothon.survey.application.domain.dto.response;

public record ReadOcrResponse(
        String applicationPurpose,

        String applicationParticipants // 신청서 아이디
) {
    public static ReadOcrResponse from(String applicationPurpose, String applicationParticipants) {
        return new ReadOcrResponse(applicationPurpose, applicationParticipants);
    }
}
