package org.semothon.survey.application;

import org.semothon.survey.core.configuration.ClovaOcrClientConfiguration;
import org.semothon.survey.application.domain.dto.response.ClovaOcrResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
        name = "clovaOcrClient",
        url = "${clova.ocr.url}", // application.properties에 실제 URL 등록
        configuration = ClovaOcrClientConfiguration.class
)
public interface ClovaOcrClient {
    @PostMapping(value = "/general", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ClovaOcrResponse extractText(
            @RequestPart("message") String message,
            @RequestPart("file") MultipartFile file
    );
}
