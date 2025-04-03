package org.semothon.survey.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.ClovaOcrClient;
import org.semothon.survey.application.domain.dto.response.ClovaOcrResponse;
import org.semothon.survey.application.domain.dto.response.ReadOcrResponse;
import org.semothon.survey.application.util.MessagePayloadBuilder;
import org.semothon.survey.application.util.OcrResultParser;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class OcrUseCase {
    private final ClovaOcrClient clovaOcrClient;
    private final MessagePayloadBuilder messagePayloadBuilder;
    private final OcrResultParser ocrResultParser;

    public ReadOcrResponse execute(MultipartFile file) {
        // 메시지 JSON 생성
        String messageJson = messagePayloadBuilder.buildMessagePayload(file);

        // Clova OCR API 호출
        ClovaOcrResponse response = clovaOcrClient.extractText(messageJson, file);

        // OCR 응답을 줄바꿈 단위로 그룹화
        String rawOcrResult = ocrResultParser.buildGroupedText(response);

        // 정규표현식으로 추가 정보 추출
        OcrResultParser.ExtractedInfo extractedInfo = ocrResultParser.extractInfo(rawOcrResult);

        // 결과 조합
        StringBuilder finalResult = new StringBuilder();
//        finalResult.append(rawOcrResult);
//        finalResult.append("\n[추출된 정보]\n");
        finalResult.append("구체적으로: ").append(extractedInfo.getDetailText()).append("\n");
        finalResult.append("사용 인원: ").append(extractedInfo.getUsageNumber()).append("\n");

        // DTO 생성 via from 메서드
        return ReadOcrResponse.from(extractedInfo.getDetailText(), extractedInfo.getUsageNumber());
    }
}
