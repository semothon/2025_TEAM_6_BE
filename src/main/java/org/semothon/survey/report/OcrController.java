package org.semothon.survey.report;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.core.support.response.ApiResponse;
import org.semothon.survey.report.domain.service.OcrUseCase;
import org.springframework.http.ResponseEntity;
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
    public ApiResponse<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String result = ocrService.execute(file);
        return ApiResponse.success(result);
    }
}
