package org.semothon.survey.core.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileConversionAndUploadUseCase {
    private final FileConversionService fileConversionService;
    private final S3UploadService s3UploadService;

    public String convertAndUpload(MultipartFile file) {
        try {
            // 파일을 PNG로 변환
            byte[] pngBytes = fileConversionService.convertFileToPng(file);
            // 변환된 파일을 S3에 업로드 (파일명, 콘텐츠 타입 설정)
            return s3UploadService.uploadFile(pngBytes, file.getOriginalFilename(), "image/png");
        } catch (Exception e) {
            throw new RuntimeException("파일 변환 및 업로드 실패", e);
        }
    }
}
