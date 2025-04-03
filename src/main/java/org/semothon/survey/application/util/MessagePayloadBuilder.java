package org.semothon.survey.application.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.dto.ImagePayload;
import org.semothon.survey.application.domain.dto.MessagePayload;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MessagePayloadBuilder {
    private final ObjectMapper objectMapper;

    public String buildMessagePayload(MultipartFile file) {
        List<ImagePayload> images = java.util.List.<ImagePayload>of(
                new ImagePayload(getFileExtension(file.getOriginalFilename()), removeExtension(file.getOriginalFilename()))
        );
        MessagePayload messagePayload = new MessagePayload(
                "V2", // 권장 엔진 버전
                UUID.randomUUID().toString(), // 고유 requestId
                System.currentTimeMillis(),
                "ko",
                images
        );
        try {
            return objectMapper.writeValueAsString(messagePayload);
        } catch (Exception e) {
            throw new RuntimeException("OCR 메시지 생성 실패", e);
        }
    }

    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return filename.substring(lastDotIndex + 1).toLowerCase();
        }
        return "jpg"; // 기본값
    }

    private String removeExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return filename.substring(0, lastDotIndex);
        }
        return filename;
    }
}