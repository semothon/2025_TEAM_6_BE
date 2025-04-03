package org.semothon.survey.application.domain.dto.response;

public record ReadOcrResponse(
        String applicationPurpose,

        String applicationParticipant,// 신청서 아이디

        String applicationUrl
) {
    public static ReadOcrResponse from(String applicationPurpose, String applicationParticipants, String applicationUrl) {
        return new ReadOcrResponse(applicationPurpose, applicationParticipants, applicationUrl);
    }
}
