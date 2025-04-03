package org.semothon.survey.application.presentation.response;

public record ReadOcrResponse(
        Long reportId,

        Long applicaitonId // 신청서 아이디
) { }
