package org.semothon.survey.application.presentation.request;


public record RejectRequest(

        Long applicationId,

        String applicationRejectReason
) {
}
