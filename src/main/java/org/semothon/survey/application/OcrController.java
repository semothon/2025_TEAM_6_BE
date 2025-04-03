package org.semothon.survey.application;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.dto.response.ReadOcrResponse;
import org.semothon.survey.core.support.response.ApiResponse;
import org.semothon.survey.application.domain.service.OcrUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ocr")
@RequiredArgsConstructor
public class OcrController {
    private final OcrUseCase ocrService;

    @PostMapping("/upload")
    public ApiResponse<ReadOcrResponse> uploadImage(@RequestParam("file") MultipartFile file) {
        ReadOcrResponse result = ocrService.execute(file);
        return ApiResponse.success(result);
    }
}
