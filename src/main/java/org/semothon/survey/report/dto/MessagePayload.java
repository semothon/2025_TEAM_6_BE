package org.semothon.survey.report.dto;

import java.util.List;

public record MessagePayload (
        String version,
        String requestId,
        long timestamp,
        String lang,
        List<ImagePayload> images
) { }
