package org.semothon.survey.application.util;

import org.semothon.survey.report.dto.response.ClovaOcrResponse;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OcrResultParser {

    public String buildGroupedText(ClovaOcrResponse response) {
        StringBuilder resultBuilder = new StringBuilder();

        if (response.images() != null) {
            response.images().forEach(image -> {
                if (image.fields() != null) {
                    StringBuilder lineBuilder = new StringBuilder();
                    for (ClovaOcrResponse.ImageResult.Field field : image.fields()) {
                        lineBuilder.append(field.inferText()).append(" ");
                        if (field.lineBreak() != null && field.lineBreak()) {
                            resultBuilder.append(lineBuilder.toString().trim()).append("\n");
                            lineBuilder.setLength(0);
                        }
                    }
                    if (lineBuilder.length() > 0) {
                        resultBuilder.append(lineBuilder.toString().trim()).append("\n");
                    }
                }
            });
        }
        return resultBuilder.toString();
    }

    public ExtractedInfo extractInfo(String ocrResult) {
        String detailText = "";
        String usageNumber = "";

        // (구체적으로): 이후의 문장을 추출
        Pattern detailPattern = Pattern.compile("\\(구체적으로\\):\\s*(.*)");
        Matcher detailMatcher = detailPattern.matcher(ocrResult);
        if (detailMatcher.find()) {
            detailText = detailMatcher.group(1).trim();
        }

        // 신청인 사용 인원 ( ... ) 에서 숫자만 추출
        Pattern usagePattern = Pattern.compile("신청인 사용 인원\\s*\\(\\s*(\\d+)");
        Matcher usageMatcher = usagePattern.matcher(ocrResult);
        if (usageMatcher.find()) {
            usageNumber = usageMatcher.group(1).trim();
        }

        return new ExtractedInfo(detailText, usageNumber);
    }

    public static class ExtractedInfo {
        private final String detailText;
        private final String usageNumber;

        public ExtractedInfo(String detailText, String usageNumber) {
            this.detailText = detailText;
            this.usageNumber = usageNumber;
        }

        public String getDetailText() {
            return detailText;
        }

        public String getUsageNumber() {
            return usageNumber;
        }
    }
}
