package org.semothon.survey.application.presentation;

import lombok.RequiredArgsConstructor;
import org.semothon.survey.application.domain.dto.response.ReadOcrResponse;
import org.semothon.survey.core.support.response.ApiResponse;
import org.semothon.survey.application.domain.service.ApplicationPdfUploadUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ApplicationPdfUploadController {
    private final ApplicationPdfUploadUseCase ocrService;

    @PostMapping("api/application/upload/pdf")
    public ApiResponse<ReadOcrResponse> uploadImage(@RequestParam("file") MultipartFile file) {
        ReadOcrResponse result = ocrService.executeOCR(file);
        return ApiResponse.success(result);
    }
}
