package org.semothon.survey.application.domain.response;

public record ReadOcrResponse(
        String applicationPurpose,

        Integer applicationParticipants // 신청서 아이디
) { }
