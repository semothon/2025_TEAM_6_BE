package org.semothon.survey.application.presentation.request;


public record ApplicationRejectRequest(

        Long applicationId,

        String applicationRejectReason
) {
}
